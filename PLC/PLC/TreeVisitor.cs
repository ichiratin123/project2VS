/*using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PLC
{
    public class TreeVisitor : PGBaseVisitor<(Type Type, object Value)>
    {
        SymbolTable symbolTable = new SymbolTable();

        private string ToString(object value)
        {
            return value?.ToString() ?? "";
        }

        public override (Type Type, object Value) VisitProgram([NotNull] PGParser.ProgramContext context)
        {
            StringBuilder sb = new StringBuilder();
            foreach (var stat in context.stat())
            {
                var result = Visit(stat);
                // Log for debugging purposes
                Console.WriteLine("in for: " + result);

                // Depending on your requirements, you might want to print every result, or handle it based on type
                switch (result.Type)
                {
                    case Type.Int:
                        // Adds 'PRINT' only for integers or you might want to handle other types similarly
                        sb.AppendLine($"PRINT {result.Value}");
                        break;
                    case Type.Float:
                        sb.AppendLine($"PRINT {result.Value}"); // Handle floats similarly if needed
                        break;
                    case Type.String:
                        sb.AppendLine($"PRINT \"{result.Value}\""); // Ensure strings are quoted if printed
                        break;
                    case Type.Bool:
                        sb.AppendLine($"PRINT {result.Value.ToString().ToUpper()}"); // Print booleans in uppercase
                        break;
                    case Type.Error:
                        // Handle error or do nothing
                        sb.AppendLine("ERROR detected in input.");
                        break;
                    default:
                        // Optionally handle other cases or unknown types
                        sb.AppendLine($"Unhandled type: {result.Type}");
                        break;
                }
            }
            return (Type.String, sb.ToString());
        }


        public override (Type Type, object Value) VisitDeclarattion([NotNull] PGParser.DeclarattionContext context)
        {
            var type = Visit(context.ptype());
            foreach (var identifier in context.ID())
            {
                symbolTable.Add(identifier.Symbol, type.Type);
            }
            return (Type.Error, 0);
        }

        public override (Type Type, object Value) VisitWriteExpr([NotNull] PGParser.WriteExprContext context)
        {
            foreach (var expr in context.expr())
            {
                var result = Visit(expr);
                if (result.Type == Type.Error)
                {
                    continue;
                }
                Console.WriteLine(ToString(result.Value));
            }
            return (Type.Error, 0);
        }


        public override (Type Type, object Value) VisitEmptyExpr([NotNull] PGParser.EmptyExprContext context)
        {
            var value = Visit(context.expr());
            if (value.Type != Type.Error) Console.WriteLine(value.Value);
            else
            {
                Errors.PrintAndClearErrors();
            }
            return (Type.Error, 0);
        }
        public override (Type Type, object Value) VisitPtype([NotNull] PGParser.PtypeContext context)
        {
            if (context.type.Text.Equals("int"))
            {
                return (Type.Int, 0);
            }
            else if (context.type.Text.Equals("float"))
            {
                return (Type.Float, 0.0f);
            }
            else if (context.type.Text.Equals("string"))
            {
                return (Type.String, "");
            }
            else if (context.type.Text.Equals("bool"))
            {
                return (Type.Bool, false);
            }
            else
            {
                return (Type.Error, 0);
            }
        }

        public override (Type Type, object Value) VisitString([NotNull] PGParser.StringContext context)
        {
            return (Type.String, context.STRING().GetText());
        }

        public override (Type Type, object Value) VisitBoolean([NotNull] PGParser.BooleanContext context)
        {
            return (Type.Bool, bool.Parse(context.BOOL().GetText()));
        }

        public override (Type Type, object Value) VisitFloat([NotNull] PGParser.FloatContext context)
        {
            return (Type.Float, float.Parse(context.FLOAT().GetText()));
        }
        public override (Type Type, object Value) VisitInt([NotNull] PGParser.IntContext context)
        {
            var value = int.Parse(context.INT().GetText());
            return (Type.Int, $"PUSH {value}\n");
        }
        public override (Type Type, object Value) VisitId([NotNull] PGParser.IdContext context)
        {
            return symbolTable[context.ID().Symbol];
        }
        public override (Type Type, object Value) VisitParenthesis([NotNull] PGParser.ParenthesisContext context)
        {
            return Visit(context.expr());
        }

        public override (Type Type, object Value) VisitAddSubConcat([NotNull] PGParser.AddSubConcatContext context)
        {
            var leftType = Visit(context.expr(0));
            var rightType = Visit(context.expr(1));
            if (leftType.Type == Type.Error || rightType.Type == Type.Error)
            {
                return (Type.Error, 0);
            }

            if (leftType.Type == Type.String && rightType.Type == Type.String)
            {
                if (context.op.Type == PGParser.ADD)
                {
                    Errors.ReportError(context.ADD().Symbol, $"Symbol '+' won't work with strings at line {context.Start.Line}.");
                    Errors.PrintAndClearErrors();
                }
                return (leftType.Type, "");
            }

            if (leftType.Type == Type.String || rightType.Type == Type.String)
            {
                Errors.ReportError(context.CONCAT().Symbol, $"Operation between string and non-string types not allowed at line {context.Start.Line}.");
                return (Type.Error, 0);
            }
            if (leftType.Type == Type.Float || rightType.Type == Type.Float)
            {
                return (Type.Float, 0.0f);

            }
            else
            {
                return (Type.Int, 0);
            }
        }



        public override (Type Type, object Value) VisitMulDivMod([NotNull] PGParser.MulDivModContext context)
        {
            var leftType = Visit(context.expr(0));
            var rightType = Visit(context.expr(1));

            if (leftType.Type == Type.Error || rightType.Type == Type.Error)
            {
                return (Type.Error, 0);
            }
            if (context.op.Type == PGParser.MOD && (leftType.Type == Type.Float || rightType.Type == Type.Float))
            {
                Errors.ReportError(context.MOD().Symbol, $"Modulus operation not supported with float operands at line {context.Start.Line}.");
                return (Type.Error, 0);
            }

            if (leftType.Type == Type.Float || rightType.Type == Type.Float)
            {
                return (Type.Float, 0.0f);
            }
            else
            {
                return (Type.Int, 0);
            }
        }


        public override (Type Type, object Value) VisitAssign([NotNull] PGParser.AssignContext context)
        {
            var right = Visit(context.expr());
            var variable = symbolTable[context.ID().Symbol];

            if (variable.Type == Type.Error || right.Type == Type.Error) return ((Type.Error, 0));

            if (variable.Type == Type.Int && right.Type == Type.Float)
            {
                Errors.ReportError(context.ID().Symbol, $"Variable '{context.ID().GetText()}' type is int, but the assigned value is float.");
                return (Type.Error, 0);
            }
            if (variable.Type == Type.Float && right.Type == Type.Int)
            {
                return (Type.Float, 0.0f);
            }
            else
            {
                symbolTable[context.ID().Symbol] = right;
                return right;
                //return variable;
            }
        }

        // public override (Type Type, object Value) VisitCompare([NotNull] PGParser.CompareContext context)
        // {
        //     var left = Visit(context.expr()[0]);
        //     var right = Visit(context.expr()[1]);

        //     if (left.Type == Type.Error || right.Type == Type.Error) return ((Type.Error, 0));

        //     if (context.op.Type == PGParser.LT)
        //     {
        //         return (Type.Bool, false);
        //     }
        //     else if (context.op.Type == PGParser.LE)
        //     {
        //         return (Type.Bool, false);
        //     }
        //     else if (context.op.Type == PGParser.GT)
        //     {
        //         return (Type.Bool);
        //     }
        //     else if (context.op.Type == PGParser.GE)
        //     {
        //         return (Type.Bool);
        //     }
        //     else if (context.op.Type == PGParser.EQ)
        //     {
        //         return (Type.Bool);
        //     }
        //     else
        //     {
        //         return (Type.Bool);
        //     }

        // }

        public override (Type Type, object Value) VisitTernary([NotNull] PGParser.TernaryContext context)
        {
            var left = Visit(context.expr(0));
            var right1 = Visit(context.expr(1));
            var right2 = Visit(context.expr(2));

            if (left.Type == Type.Error) return ((Type.Error, 0.0f));

            if (left.Type != Type.Bool)
            {
                Errors.ReportError(context.expr(0).Start, "Condition in ternary operation is not a boolean.");
                return (Type.Error, 0);
            }

            if (right1.Type != right2.Type)
            {
                if ((right1.Type == Type.Float && right2.Type == Type.Int) || (right2.Type == Type.Float && right1.Type == Type.Int))
                {
                    return (Type.Int, 0);
                }
                else Console.WriteLine("Incorrect type in result expression");
            }

            return (Type.Int, 0);
            // if ((bool)left.Value)
            // {
            //     return Visit(context.expr(1));
            // }
            // else
            // {
            //     return Visit(context.expr(2));
            // }

        }

    }
}
*/