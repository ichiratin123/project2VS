using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Reflection.Metadata.Ecma335;
using System.Text;
using System.Threading.Tasks;

namespace PLC
{
    public class CodeGeneratorVisitor : PGBaseVisitor<String>
    {
        private ParseTreeProperty<Type> Types;
        private SymbolTable SymbolTable;
        static int labelCount = 0;
        public CodeGeneratorVisitor(ParseTreeProperty<Type> types, SymbolTable symbolTable)
        {
            Types = types;
            SymbolTable = symbolTable;
        }

        public override string VisitProgram([NotNull] PGParser.ProgramContext context)
        {
            StringBuilder sb = new StringBuilder();
            foreach (var statement in context.stat())
            {
                sb.Append(Visit(statement));
            }
            return sb.ToString();
        }

        public override string VisitEmptyExpr([NotNull] PGParser.EmptyExprContext context)
        {
            var code = Visit(context.expr());
            var type = Types.Get(context.expr());
            /*return code + $"PRINT {(type == Type.Int ? "I" : (type == Type.Float ? "F" : "B"))}\n";*/
            return code + "POP\n";
        }

        public override string VisitPtype([NotNull] PGParser.PtypeContext context)
        {
            return context.type.Text == "int" ? "I" : context.type.Text == "float" ? "F" : context.type.Text == "string" ? "S" : "B";
        }

        public override string VisitInt([NotNull] PGParser.IntContext context)
        {
            return $"PUSH I {context.INT().Symbol.Text}\n";
            /*return $"PUSH I {context.INT().GetText}\n";*/
        }

        public override string VisitFloat([NotNull] PGParser.FloatContext context)
        {
            return $"PUSH F {context.FLOAT().Symbol.Text}\n";
            /*return $"PUSH F {context.FLOAT().GetText}\n";*/
        }

        public override string VisitBoolean([NotNull] PGParser.BooleanContext context)
        {
            return $"PUSH B {context.BOOL().Symbol.Text}\n";
            /*return $"PUSH B {context.BOOL().GetText}\n";*/
        }

        public override string VisitString([NotNull] PGParser.StringContext context)
        {
            return $"PUSH S {context.STRING().Symbol.Text}\n";
            /*return $"PUSH S {context.STRING().GetText}\n";*/
        }

        public override string VisitId([NotNull] PGParser.IdContext context)
        {
            return $"LOAD {context.ID().Symbol.Text}\n";
        }

        public override string VisitDeclarattion([NotNull] PGParser.DeclarattionContext context)
        {
            StringBuilder sb = new StringBuilder();
            string constant = Visit(context.ptype());
            foreach (var name in context.ID())
            {
                string check = "";
                if (constant == "I") check = "0";
                else if (constant == "F") check = "0.0";
                else if (constant == "S") check = "\"\"";
                else check = "false";

                sb.Append($"PUSH {constant} {check}\n");
                sb.Append($"SAVE {constant} {name.Symbol.Text}\n");
            }
            return sb.ToString();
        }

        public override string VisitParenthesis([NotNull] PGParser.ParenthesisContext context)
        {
            return Visit(context.expr());
        }

        public override string VisitMulDivMod([NotNull] PGParser.MulDivModContext context)
        {
            var left = Visit(context.expr()[0]);
            var right = Visit(context.expr()[1]);

            Type leftType = Types.Get(context.expr()[0]);
            Type rightType = Types.Get(context.expr()[1]);

            StringBuilder sb = new StringBuilder();
            sb.Append(left);
            if (leftType == Type.Int && rightType == Type.Float)
            {
                left = left + "ITOF\n";
            }
            sb.Append(right);
            if (rightType == Type.Int && leftType == Type.Float)
            {
                right = right + "ITOF\n";
            }

            if (context.op.Type == PGParser.MUL)
            {
                return left + right + "MUL\n";
            }
            else if (context.op.Type == PGParser.DIV)
            {
                return left + right + "DIV\n";
            }
            else
            {
                return left + right + "MOD\n";
            }
        }

        public override string VisitAddSubConcat([NotNull] PGParser.AddSubConcatContext context)
        {
            var left = Visit(context.expr()[0]);
            var right = Visit(context.expr()[1]);

            Type leftType = Types.Get(context.expr()[0]);
            Type rightType = Types.Get(context.expr()[1]);

            StringBuilder sb = new StringBuilder();
            sb.Append(left);
            if (leftType == Type.Int && rightType == Type.Float)
            {
                sb.Append("ITOF\n");
            }
            sb.Append(right);
            if (rightType == Type.Int && leftType == Type.Float)
            {
                sb.Append("ITOF\n");
            }
            if (context.op.Type == PGParser.ADD)
            {
                sb.Append("ADD\n");
            }
            else if (context.op.Type == PGParser.SUB)
            {
                sb.Append("SUB\n");
            }
            else
            {
                sb.Append("CONCAT\n");
            }

            return sb.ToString();
        }


        public override string VisitWriteExpr([NotNull] PGParser.WriteExprContext context)
        {
            StringBuilder sb = new StringBuilder();
            foreach (var expression in context.expr())
            {
                var idContext = expression as PGParser.IdContext;
                if (idContext != null)
                {
                    /*sb.Append($"LOAD {idContext.ID().GetText()}\n");*/
                }
                sb.Append(Visit(expression));
            }
            sb.Append($"PRINT {context.expr().Length}\n");

            return sb.ToString();

        }

        public override string VisitAssign([NotNull] PGParser.AssignContext context)
        {
            var exprType = Types.Get(context.expr());        
            var variableType  = SymbolTable[context.ID().Symbol];
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.Append(Visit(context.expr()));

            if (exprType == Type.Int && variableType == Type.Float)
            {
                stringBuilder.Append("ITOF\n");
            }

            var check = "";
            switch (variableType) {
                case Type.Int: check = "I"; break;
                case Type.Float: check = "F"; break;
                case Type.Bool: check = "B"; break;
                case Type.String:
                    check = "S"; break;
            }
            stringBuilder.Append($"SAVE {check} {context.ID().Symbol.Text}\n");
            stringBuilder.Append($"LOAD {context.ID().Symbol.Text}\n");
            return stringBuilder.ToString();
        }

        public override string VisitUminus([NotNull] PGParser.UminusContext context)
        {
            var u = Visit(context.expr());
            return $"{u}UMINUS\n";
        }

        public override string VisitReadExpr([NotNull] PGParser.ReadExprContext context)
        {
            StringBuilder sb = new StringBuilder();
            foreach (var id in context.ID())
            {          
                var x = SymbolTable[id.Symbol];
                var type = "";
                switch (x) { 
                    case Type.Int: type = "I"; break;
                    case Type.Float: type = "F"; break;
                    case Type.Bool: type = "B"; break;
                    case Type.String: type = "S"; break;
                    default: break;
                }

                sb.AppendLine($"READ {type}");
                sb.AppendLine($"SAVE {type} {id.GetText()}");
            }
            return sb.ToString();
        }

        public override string VisitCompare([NotNull] PGParser.CompareContext context)
        {
            var left = Visit(context.expr()[0]);
            var right = Visit(context.expr()[1]);
            var operatorType = context.op.Type;

            Type leftType = Types.Get(context.expr()[0]);
            Type rightType = Types.Get(context.expr()[1]);

            StringBuilder sb = new StringBuilder();

            if ((leftType == Type.Int && rightType == Type.Float))
            {
                sb.Append(left);
                sb.Append("ITOF\n");
                sb.Append(right);
            }
            else if (leftType == Type.Float && rightType == Type.Int)
            {
                sb.Append(right);
                sb.Append("ITOF\n");
                sb.Append(left);
            }
            else {
                sb.Append(left);
                sb.Append(right);
            }

            switch (operatorType)
            {
                case PGParser.LT:
                    sb.Append("LT\n");
                    break;
                case PGParser.GT:
                    sb.Append("GT\n");
                    break;
                case PGParser.EQ:
                    sb.Append("EQ\n");
                    break;
                case PGParser.NEQ:
                    sb.Append("EQ\n");
                    sb.Append("NOT\n");
                    break;
                default:
                    throw new InvalidOperationException("Unsupported comparison operator.");
            }

            return sb.ToString();
        }

        public override string VisitNot([NotNull] PGParser.NotContext context)
        {
            var exprCode = Visit(context.expr());
            Type exprType = Types.Get(context.expr());
            if (exprType != Type.Bool)
            {
                throw new InvalidOperationException("Logical NOT operation applied to a non-boolean type.");
            }

            StringBuilder sb = new StringBuilder();
            sb.Append(exprCode);
            sb.Append("NOT\n");

            return sb.ToString();
        }

        public override string VisitLogicAnd([NotNull] PGParser.LogicAndContext context)
        {
            var left = Visit(context.expr()[0]);
            var right = Visit(context.expr()[1]);
            StringBuilder sb = new StringBuilder();
            sb.Append(left);
            sb.Append(right);
            sb.Append("AND\n");
            return sb.ToString();
        }

        public override string VisitLogicOr([NotNull] PGParser.LogicOrContext context)
        {
            var left = Visit(context.expr()[0]);
            var right = Visit(context.expr()[1]);
            StringBuilder sb = new StringBuilder();
            sb.Append(left);
            sb.Append(right);
            sb.Append("OR\n");
            return sb.ToString();
        }

        public override string VisitIfCon([NotNull] PGParser.IfConContext context)
        {
            StringBuilder sb = new StringBuilder();
            string evalExpr = Visit(context.expr());
            string thenPart = Visit(context.stat(0));

            int elseLabelNum = labelCount++;
            int endLabelNum = labelCount++;

            sb.Append(evalExpr);
            sb.AppendLine($"FJMP {elseLabelNum}");

            sb.Append(thenPart);
            sb.AppendLine($"JMP {endLabelNum}");

            sb.AppendLine($"LABEL {elseLabelNum}");
            if (context.ELSE_KW() != null)
            {
                string elsePart = Visit(context.stat(1));
                sb.Append(elsePart);
            }
            sb.AppendLine($"LABEL {endLabelNum}");

            return sb.ToString();
        }

        public override string VisitBlockStat([NotNull] PGParser.BlockStatContext context)
        {
            StringBuilder sb = new StringBuilder();
            foreach (var statement in context.stat())
            {
                sb.Append(Visit(statement));
            }
            return sb.ToString();
        }

        public override string VisitWhileLoop([NotNull] PGParser.WhileLoopContext context)
        {
            StringBuilder sb = new StringBuilder();
            int loopLabel = labelCount++;
            int endLabel = labelCount++;
            sb.AppendLine($"LABEL {loopLabel}");
            string condition = Visit(context.expr());
            sb.Append(condition);
            sb.AppendLine($"FJMP {endLabel}");
            string body = Visit(context.stat());
            sb.Append(body);
            sb.AppendLine($"JMP {loopLabel}");
            sb.AppendLine($"LABEL {endLabel}");

            return sb.ToString();
        }

        

    }
}
