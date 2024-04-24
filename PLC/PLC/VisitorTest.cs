using Antlr4.Runtime.Misc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PLC
{
    public class VisitorTest : PGBaseVisitor<string>
    {
        
        private int labelCounter = 0;
        private int GetNewLabel()
        {
            return labelCounter++;
        }
        public override string VisitInt([NotNull] PGParser.IntContext context)
        {
            var value = context.INT().GetText();
            return $"PUSH I {value}\n";
        }
        public override string VisitString([NotNull] PGParser.StringContext context)
        {
            var value = context.STRING().GetText().Trim('"');
            return $"PUSH S \"{value}\"\n";
        }
        public override string VisitBoolean([NotNull] PGParser.BooleanContext context)
        {
            var value = context.BOOL().GetText();
            return $"PUSH B {value}\n";
        }
        public override string VisitFloat([NotNull] PGParser.FloatContext context)
        {
            var value = context.FLOAT().GetText();
            return $"PUSH F {value}\n";
        }
        public override string VisitParenthesis([NotNull] PGParser.ParenthesisContext context)
        {
            return Visit(context.expr());
        }

        public override string VisitDeclarattion([NotNull] PGParser.DeclarattionContext context)
        {
            var type = VisitPtype(context.ptype());
            StringBuilder sb = new StringBuilder();

            foreach (var idCtx in context.ID())
            {
                var id = idCtx.GetText();
                string defaultValue = GetDefaultValueByType(type);
                sb.AppendLine($"PUSH {type} {defaultValue}");
                sb.AppendLine($"SAVE {id}");
            }

            return sb.ToString();
        }

        public override String VisitPtype([NotNull] PGParser.PtypeContext context)
        {
            return context.type.Text switch
            {
                "int" => "I",
                "float" => "F",
                "string" => "S",
                "bool" => "B",
                _ => throw new ArgumentException("Unsupported type: " + context.type.Text)
            };
        }

        private string GetDefaultValueByType(string type)
        {
            switch (type)
            {
                case "I":
                    return "0";
                case "F":
                    return "0.0f";
                case "S":
                    return "\"\"";
                case "B":
                    return "true";
                default:
                    throw new ArgumentException("Unsupported type: " + type);
            }
        }

        public override string VisitAddSubConcat([NotNull] PGParser.AddSubConcatContext context)
        {
            var left = Visit(context.expr(0));
            var right = Visit(context.expr(1));
            var type = "I";
            if (Itof(left) || Itof(right))
            {
                if (Itof(left) && !Itof(right))
                {
                    right = $"{right}ITOF\n";
                    type = "F";

                }
                else
                {
                    left = $"{left}ITOF\n";
                    type = "F";
                }

            }

            string operation = context.op.Text switch
            {
                "+" => "ADD",
                "-" => "SUB",
                "." => "CONCAT",
                _ => throw new NotSupportedException("Unsupported operation")
            };
            return $"{left}{right}{operation} {type}\n";
        }

        public bool Itof(string code)
        {
            if(code.Contains("F")){
                return true;}
            return false;
        }

        // public override string VisitItof ([NotNull] PGParser.ItofContext context) {
        //     string intValue = Visit(context.expr());
        //     if (int.TryParse(intValue, out int result)) {
        //         return result.ToString("F");
        //     } else {
        //         return "Invalid input";
        //     }
        // }

        // private string getVartype(PGParser.ExprContext context)
        // {
        //     if (context is PGParser.IntContext)
        //         return "I";
        //     else if (context is PGParser.FloatContext)
        //         return "F";
        //     else if (context is PGParser.StringContext)
        //         return "S";
        //     else
        //         return "";
        // }

        public override string VisitMulDivMod([NotNull] PGParser.MulDivModContext context)
        {
            var left = Visit(context.expr(0));
            var right = Visit(context.expr(1));
            var type ="I";
            if (Itof(left) || Itof(right))
            {
                if (Itof(left) && !Itof(right))
                {
                    right = $"{right}ITOF\n";
                    type = "F";
                }
                else
                {
                    left = $"{left}ITOF\n";
                    type = "F";
                }
            }
            string operation = context.op.Text switch
            {
                "*" => "MUL",
                "/" => "DIV",
                "%" => "MOD",
                _ => throw new NotSupportedException("Unsupported operation")
            };
            
            return $"{left}{right}{operation} {type}\n";
        }

        public override string VisitUminus([NotNull] PGParser.UminusContext context)
        {
            var e = Visit(context.expr());
            return $"{e}UMINUS\n";
        }
        
        public override string VisitNot([NotNull] PGParser.NotContext context)
        {
            var e = Visit(context.expr());
            return $"{e}NOT\n";
        }

        // public override string VisitCompare([NotNull] PGParser.CompareContext context)
        // {
        //     var left = Visit(context.expr(0));
        //     var right = Visit(context.expr(1));
        //     var leftType = getVartype(context.expr(0));
        //     var rightType = getVartype(context.expr(1));
        //     string operation = context.op.Text switch
        //     {
        //         "<" => "LT",
        //         ">" => "GT",
        //         "<=" => "LE",
        //         ">=" => "GE",
        //         "==" => "EQ",
        //         "!=" => "NOTEQ",
        //         _ => throw new NotSupportedException("Unsupported relational operation")
        //     };
            

        //     return $"{left}{right}{operation}\n";
        // }

        // public override string VisitLogic([NotNull] PGParser.LogicContext context)
        // {
        //     var left = Visit(context.expr(0));
        //     var right = Visit(context.expr(1));
        //     var leftType = getVartype(context.expr(0));
        //     var rightType = getVartype(context.expr(1));
        //     string operation = context.op.Text switch
        //     {
        //         "&&" => "AND",
        //         "||" => "OR",
        //         _ => throw new NotSupportedException("Unsupported logic operation")
        //     };
            

        //     return $"{left}{right}{operation}\n";
        // }

        public override string VisitAssign([NotNull] PGParser.AssignContext context)
        {
            var id = context.ID().GetText();
            var exprCode = Visit(context.expr());
            return $"{exprCode}SAVE {id}\n";
        }


        public override string VisitIfCon([NotNull] PGParser.IfConContext context)
        {
            
            int elseLabel = GetNewLabel();
            int endLabel = GetNewLabel();
            StringBuilder sb = new StringBuilder();

             sb.Append(Visit(context.expr())); 
            sb.Append($"fjmp {elseLabel}\n"); 
            sb.Append(Visit(context.stat(0))); 
            sb.Append($"jmp {endLabel}\n"); 
            sb.Append($"label {elseLabel}\n"); 
            if (context.stat().Length > 1)
            {
                sb.Append(Visit(context.stat(1)));
            }
            sb.Append($"label {endLabel}\n"); 
            return sb.ToString();
        }


        public override string VisitWhileLoop([NotNull] PGParser.WhileLoopContext context)
        {
            int startLabel = GetNewLabel();
            int endLabel = GetNewLabel();
            StringBuilder sb = new StringBuilder();
            sb.Append($"label {startLabel}\n"); 
            sb.Append(Visit(context.expr())); 
            sb.Append($"fjmp {endLabel}\n"); 
            sb.Append(Visit(context.stat()));
            sb.Append($"jmp {startLabel}\n"); 
            sb.Append($"label {endLabel}\n"); 
            return sb.ToString();
        }

        public override string VisitReadExpr([NotNull] PGParser.ReadExprContext context)
        {
            StringBuilder sb = new StringBuilder();
            foreach (var id in context.ID())
            {
                sb.AppendLine($"READ {id.GetText()}"); 
                sb.AppendLine($"SAVE {id.GetText()}"); 
            }
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
        public override string VisitWriteExpr([NotNull] PGParser.WriteExprContext context)
        {
            StringBuilder sb = new StringBuilder();
            int exprCount = context.expr().Count();
            foreach (var exprContext in context.expr())
            {
                sb.Append(Visit(exprContext));
                if (exprContext is PGParser.IdContext idContext)
                {
                    string varName = idContext.GetText();
                    sb.AppendLine($"LOAD {varName}");
                }
            }
            if (exprCount > 1)
            {
                for (int i = 0; i < exprCount - 1; i++)
                {
                    sb.AppendLine("POP");
                }
            }

            sb.AppendLine($"PRINT {exprCount}");

            return sb.ToString();
        }

    public override string VisitEmptyExpr([NotNull] PGParser.EmptyExprContext context)
        {
            return Visit(context.expr()) + "";
        }
        public override string VisitProgram([NotNull] PGParser.ProgramContext context)
        {
            StringBuilder sb = new StringBuilder();
            foreach (var expr in context.stat())
            {
                var code = Visit(expr);
                sb.Append(code);
            }
            sb.Append("POP");
            return sb.ToString();
        }
    }
}
