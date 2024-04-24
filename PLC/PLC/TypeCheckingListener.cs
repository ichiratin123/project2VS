using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection.Metadata.Ecma335;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime;
using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;

namespace PLC
{
    public class TypeCheckingListener : PGBaseListener
    {
        public SymbolTable SymbolTable { get; } = new SymbolTable();
        public ParseTreeProperty<Type> Types { get; } = new ParseTreeProperty<Type>();

        private Type Require(Type have, Type want, IToken token)
        {
            if (have == want) return have;
            if (have == Type.Int && want == Type.Float) return Type.Float;
            Errors.ReportError(token, $"{token.Text} requires {want}, but we have {have}");
            return Type.Error;
        }

        private Type RequireIntOrFloat(Type have, IToken token){
            if (have == Type.Int) return have;
            return Require(have, Type.Float, token);
        }

        public static Type Union(Type a, Type b)
        {
            if (a == Type.Error || b == Type.Error)
            {
                return Type.Error;
            }

            if (a == b)
            {
                return a;
            }

            if ((a == Type.Int && b == Type.Float) || (a == Type.Float && b == Type.Int))
            {
                return Type.Float;
            }
            else if ((a == Type.String || b == Type.String) && (a != Type.Bool && b != Type.Bool))
            {
                return Type.String;
            }
            else if (a == Type.Bool || b == Type.Bool)
            {
                return Type.Error;
            }
            return Type.Error;
        }

        public override void ExitDeclarattion([NotNull] PGParser.DeclarattionContext context)
        {
            var type = Types.Get(context.ptype());
            foreach (var identifier in context.ID())
            {
                SymbolTable.Add(identifier.Symbol, type);
            }
        }

        public override void ExitPtype([NotNull] PGParser.PtypeContext context)
        {
            switch (context.type.Text)
            {
                case "int":
                    Types.Put(context, Type.Int);
                    break;
                case "float":
                    Types.Put(context, Type.Float);
                    break;
                case "string":
                    Types.Put(context, Type.String);
                    break;
                case "bool":
                    Types.Put(context, Type.Bool);
                    break;
                default:
                    throw new ArgumentException("Unknown type");
            }
        }

        public override void ExitInt([NotNull] PGParser.IntContext context)
        {
            Types.Put(context, Type.Int);
        }

        public override void ExitFloat([NotNull] PGParser.FloatContext context)
        {
            Types.Put(context, Type.Float);
        }

        public override void ExitBoolean([NotNull] PGParser.BooleanContext context)
        {
            Types.Put(context, Type.Bool);
        }

        public override void ExitString([NotNull] PGParser.StringContext context)
        {
            Types.Put(context, Type.String);
        }

        public override void ExitId([NotNull] PGParser.IdContext context)
        {
            Types.Put(context, SymbolTable[context.ID().Symbol]);
        }

        public override void ExitParenthesis([NotNull] PGParser.ParenthesisContext context)
        {
            Types.Put(context, Types.Get(context.expr()));
        }

        public override void ExitMulDivMod([NotNull] PGParser.MulDivModContext context)
        {
            var left = Types.Get(context.expr()[0]);
            var right = Types.Get(context.expr()[1]);

            left = RequireIntOrFloat(left, context.expr()[0].Start);
            right = RequireIntOrFloat(right, context.expr()[1].Start);

            if (left == Type.Error || right == Type.Error)
            {
                Types.Put(context, Type.Error);
                return;
            }

            if (context.op.Type == PGParser.MOD)
            {
                if (left == Type.Float || right == Type.Float)
                {
                    Errors.ReportError(context.MOD().Symbol, $"Operator {context.MOD().GetText()} can be used only with integers.");
                    Types.Put(context, Type.Error);
                    return;
                }
                else
                {
                    Types.Put(context, Type.Int);
                }
            }

            if (left == Type.Float || right == Type.Float)
            {
                Types.Put(context, Type.Float);
            }
            else
            {
                Types.Put(context, Type.Int);
            }
        }

        public override void ExitAddSubConcat([NotNull] PGParser.AddSubConcatContext context)
        {
            var left = Types.Get(context.expr()[0]);
            var right = Types.Get(context.expr()[1]);

            if (left == Type.Error || right == Type.Error)
            {
                Types.Put(context, Type.Error);
                return;
            }


            if (left == Type.String && right == Type.String) {
                if (context.op.Type == PGParser.ADD)
                {
                    Errors.ReportError(context.ADD().Symbol, $"Symbol '+' won't work with strings at line {context.Start.Line}.");
                    Types.Put(context, Type.Error);
                    return;
                }
            }

            if (left != Type.String || right != Type.String) {
                if (context.op.Type == PGParser.CONCAT)
                {
                    Errors.ReportError(context.CONCAT().Symbol, $"Symbol '.' only for string at line {context.Start.Line}.");
                    Types.Put(context, Type.Error);
                    return;
                }

                left = RequireIntOrFloat(left, context.expr()[0].Start);
                right = RequireIntOrFloat(right, context.expr()[1].Start);

                if (Union(left,right) == Type.Float)
                {
                    Types.Put(context, Type.Float);
                }
                else
                {
                    Types.Put(context, Type.Int);
                }
            }
        }


        public override void ExitAssign([NotNull] PGParser.AssignContext context)
        {
            var right = Types.Get(context.expr());
            var variable = SymbolTable[context.ID().Symbol];

            if (variable == Type.Error || right == Type.Error)
            {
                Types.Put(context, Type.Error);
            }
            if (variable == Type.Int && right == Type.Float)
            {
                Errors.ReportError(context.ID().Symbol, $"Variable '{context.ID().GetText()}' type is int, but the assigned value is float.");
                Types.Put(context, Type.Error);
            }
            else Types.Put(context, variable);
        }

        private bool IsComparable(Type left, Type right)
        {
            if (left == right) return true;
            if ((left == Type.Int || left == Type.Float) && (right == Type.Int || right == Type.Float))
                return true;
            return (left == Type.String && right == Type.String);
        }

        public override void ExitCompare([NotNull] PGParser.CompareContext context)
        {
            var left = Types.Get(context.expr()[0]);
            var right = Types.Get(context.expr()[1]);

            if (left == Type.Error || right == Type.Error)
            {
                Types.Put(context, Type.Error);
                return;
            }
            if (IsComparable(left, right))
            {
                if (left == Type.Float || right == Type.Float)
                {
                    Types.Put(context, Type.Bool);
                }
                else
                {
                    Types.Put(context, Type.Bool);
                }
            }
            else
            {
                Types.Put(context, Type.Error);
            }

        }

    }
}
