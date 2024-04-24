using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Intrinsics.X86;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using static PLC.VirtualMachine;

namespace PLC
{
    public class VirtualMachine
    {
        private Stack<object> stack = new Stack<object>();
        private List<string[]> code = new List<string[]>();
        Dictionary<string, object> memory = new Dictionary<string, object>();
        private Dictionary<int, int> labelToJump = new();

        public VirtualMachine(string code)
        {
            this.code = code.Split("\n\r".ToCharArray(), StringSplitOptions.RemoveEmptyEntries).Select(x => x.Split(" ")).ToList();
        }

        public void run() {
            IndexLabels();
            for (int row = 0; row < this.code.Count; row++)
            {
                var instruction = this.code[row];
                if (instruction[0].StartsWith("PUSH"))
                {

                    switch (instruction[1])
                    {
                        case "I":
                            stack.Push(int.Parse(instruction[2]));
                            break;
                        case "F":
                            stack.Push(float.Parse(instruction[2]));
                            break;
                        case "S":
                            stack.Push(string.Join(" ", instruction.Skip(2)));
                            break;
                        case "B":
                            stack.Push(bool.Parse(instruction[2]));
                            break;
                    }
                }
                else if (instruction[0].Equals("PRINT"))
                {
                    var n = int.Parse(instruction[1]);

                    List<Object> popped = new();
                    for (int i = 0; i < n; i++)
                    {
                        var value = stack.Pop();
                        //popped.Add(Convert.ToString(value));
                        popped.Add(value);
                    }
                    popped.Reverse();

                    foreach (var item in popped) {
                        if (item is int)
                        {
                            Console.WriteLine($"[int] {item}");
                        }
                        else if (item is float)
                        {
                            Console.WriteLine($"[float] {item}");
                        }
                        else if (item is string)
                        {
                            Console.WriteLine($"[string] {item}");
                        }
                        else if (item is bool)
                        {
                            Console.WriteLine($"[boolean] {item}");
                        }
                    }
                }
                else if (instruction[0].StartsWith("ADD")) Add();
                else if (instruction[0].StartsWith("SUB")) Sub();
                else if (instruction[0].StartsWith("MUL")) Mul();
                else if (instruction[0].StartsWith("DIV")) Div();
                else if (instruction[0].StartsWith("MOD")) Mod();
                else if (instruction[0].StartsWith("UMINUS")) Uminus();
                else if (instruction[0].StartsWith("CONCAT")) Concat();
                else if (instruction[0].StartsWith("AND")) And();
                else if (instruction[0].StartsWith("OR")) Or();
                else if (instruction[0].StartsWith("GT")) Gt();
                else if (instruction[0].StartsWith("LT")) Lt();
                else if (instruction[0].StartsWith("EQ")) Eq();
                else if (instruction[0].StartsWith("NOT")) Not();
                else if (instruction[0].StartsWith("ITOF")) Itof();
                else if (instruction[0].StartsWith("POP")) {
                    
                }
                else if (instruction[0].StartsWith("LOAD"))
                {
                    stack.Push(memory[instruction[1]]);
                }
                /*else if (instruction[0].StartsWith("SAVE"))
                {
                    var value = stack.Pop();
                    if (instruction[1] == "I") memory[instruction[2]] = (int)value;
                    else if (instruction[1] == "S") {
                        memory[instruction[2]] = (string)value; }
                    else if (instruction[1] == "B") memory[instruction[2]] = (bool)value;
                    else memory[instruction[2]] = (float)(value is int ? (int)value : (float)value);
                }*/

                else if (instruction[0].StartsWith("SAVE"))
                {
                    var value = stack.Pop();
                    if (instruction[1] == "I")
                    {
                        if (value is int)
                        {
                            memory[instruction[2]] = (int)value;
                        }
                        else if (value is float)
                        {
                            memory[instruction[2]] = (int)(float)value;
                        }
                        else
                        {
                            Console.WriteLine("int");
                        }
                    }
                    else if (instruction[1] == "S")
                    {
                        memory[instruction[2]] = (string)value;
                    }

                    else if (instruction[1] == "B")
                    {
                        if (value is bool)
                        {
                            memory[instruction[2]] = (bool)value;
                        }
                        else if (value is string)
                        {
                            bool boolValue;
                            if (bool.TryParse((string)value, out boolValue))
                            {
                                memory[instruction[2]] = boolValue;
                            }
                            else
                            {
                                Console.WriteLine("Bool");
                            }
                        }
                    }
                    else if (instruction[1] == "F")
                    {
                        if (value is float)
                        {
                            memory[instruction[2]] = (float)value;
                        }
                        else if (value is int)
                        {
                            memory[instruction[2]] = (float)(int)value;
                        }
                        else
                        {
                            Console.WriteLine("Float");
                        }
                    }
                    else
                    {
                        Console.WriteLine("Error: Invalid type specifier.");
                    }
                }

                else if (instruction[0].StartsWith("LABEL")) Label();
                else if (instruction[0].StartsWith("JMP")) Jmp(int.Parse(instruction[1]), ref row);
                else if (instruction[0].StartsWith("FJMP")) Fjmp(int.Parse(instruction[1]),  ref row);
                else if (instruction[0].StartsWith("READ"))
                {
                    if (instruction[1] == "I") Read(Type.Int);
                    if (instruction[1] == "F") Read(Type.Float);
                    if (instruction[1] == "S") Read(Type.String);
                    if (instruction[1] == "B") Read(Type.Bool);
                }
            }
        }

        private void IndexLabels()
        {
            int row = 0;
            foreach (var instruction in this.code)
            {
                if (instruction[0].StartsWith("LABEL"))
                {
                    var parts = instruction[0].Split(" ");
                    int label = int.Parse(instruction[1]);
                    labelToJump[label] = row;
                }
                row++;
            }
        }

        private void Fjmp(int v, ref int row)
        {
            var value = stack.Pop();
            if (value is bool booltype)
            {
                if (!Convert.ToBoolean(value))
                {
                    row = labelToJump[v];
                }
            }
        }

        private void Jmp(int v, ref int row)
        {
            row = labelToJump[v];
        }


        private void Label()
        {
            
        }

        private void Itof()
        {
            var value = stack.Pop();
            if (value is int intValue)
            {
                float floatValue = Convert.ToSingle(intValue);
                stack.Push(floatValue);
            }
        }

        private void Not()
        {
            var value = stack.Pop();
            if (value is bool)
            {
                stack.Push(!(bool)value);
            }
        }

        private void Eq()
        {
            var right = stack.Pop();
            var left = stack.Pop();
            if (left.GetType() != right.GetType())
            {
                stack.Push(false);
                return;
            }
            switch (left)
            {
                case int leftInt:
                    int rightInt = (int)right;
                    stack.Push(leftInt == rightInt);
                    break;
                case float leftFloat:
                    float rightFloat = (float)right;
                    stack.Push(leftFloat == rightFloat);
                    break;
                case string leftString:
                    string rightString = (string)right;
                    stack.Push(leftString == rightString);
                    break;
                case bool leftBool:
                    bool rightBool = (bool)right;
                    stack.Push(leftBool == rightBool);
                    break;
                default:
                    throw new InvalidOperationException("Unsupported types for equality comparison.");
            }
        }

        private void Lt()
        {
            var right = stack.Pop();
            var left = stack.Pop();
            if (IsNumeric(left) && IsNumeric(right))
            {
                float leftValue = Convert.ToSingle(left);
                float rightValue = Convert.ToSingle(right);
                bool result = leftValue < rightValue;
                stack.Push(result);
            }
            else
            {
                throw new InvalidOperationException("Comparison not supported for non-numeric types.");
            }
        }
        private bool IsNumeric(object value)
        {
            return value is int || value is float;
        }

        private void Gt()
        {
            var right = stack.Pop();
            var left = stack.Pop();
            if (IsNumeric(left) && IsNumeric(right))
            {
                float leftValue = Convert.ToSingle(left);
                float rightValue = Convert.ToSingle(right);
                bool result = leftValue > rightValue;
                stack.Push(result);
            }
            else
            {
                throw new InvalidOperationException("Comparison not supported for non-numeric types.");
            }
        }

        private void Or()
        {
            var right = stack.Pop();
            var left = stack.Pop();
            bool leftBool = Convert.ToBoolean(left);
            bool rightBool = Convert.ToBoolean(right);
            bool result = leftBool || rightBool;
            stack.Push(result);
        }


        private void And()
        {
            var right = stack.Pop();
            var left = stack.Pop();
            bool leftBool = Convert.ToBoolean(left);
            bool rightBool = Convert.ToBoolean(right);
            bool result = leftBool && rightBool;
            stack.Push(result);
        }

        private void Add()
        {
            var right = stack.Pop();
            var left = stack.Pop();
            if (left is int && right is int) stack.Push((int)left + (int)right);
            else stack.Push((float)(left is int ? (int)left : (float)left) + (float)(right is int ? (int)right : (float)right));
        }


        private void Div()
        {
            var right = stack.Pop();
            var left = stack.Pop();

            if ((left is int || left is float) && (right is int || right is float))
            {
                if (Convert.ToDouble(right) == 0)
                {
                    Console.WriteLine("Error: Division by zero is not allowed.");
                    stack.Push(left); 
                    stack.Push(right);
                    return;
                }

                float leftValue = Convert.ToSingle(left);
                float rightValue = Convert.ToSingle(right);
                float quotient = leftValue / rightValue;

                if (left is int && right is int)
                {
                    stack.Push(Convert.ToInt32(quotient)); 
                }
                else
                {
                    stack.Push(quotient);
                }
            }
        }

        private void Concat()
        {
            var right = stack.Pop();
            var left = stack.Pop();
            string leftStr = left.ToString().Replace("\"", "");
            string rightStr = right.ToString().Replace("\"", "");
            string result = leftStr + rightStr;
            stack.Push(result);

        }

        private void Uminus()
        {
            var item = stack.Pop();
            if (item is int intValue)
            {
                stack.Push(-intValue);
            }
            else if (item is float floatValue)
            {
                stack.Push(-floatValue);
            }
        }

        private void Mod()
        {
            var right = stack.Pop();
            var left = stack.Pop();

            if (left is int leftInt && right is int rightInt)
            {
                if (rightInt == 0)
                {
                    Console.WriteLine("Error: Modulo by zero is not allowed.");
                    stack.Push(left);
                    stack.Push(right);
                    return;
                }

                int result = leftInt % rightInt;
                stack.Push(result);
            }
        }

        private void Mul()
        {
            var right = stack.Pop();
            var left = stack.Pop();

            if ((left is int || left is float) && (right is int || right is float))
            {
                float leftValue = Convert.ToSingle(left);
                float rightValue = Convert.ToSingle(right);
                float product = leftValue * rightValue;

                if (left is int && right is int)
                {
                    stack.Push(Convert.ToInt32(product));
                }
                else
                {
                    stack.Push(product);
                }
            }
        }

        private void Sub()
        {
            var right = stack.Pop();
            var left = stack.Pop();
            if ((left is int || left is float) && (right is int || right is float))
            {
                float leftValue = Convert.ToSingle(left);
                float rightValue = Convert.ToSingle(right);
                float sum = leftValue - rightValue;
                if (left is int && right is int)
                {
                    stack.Push(Convert.ToInt32(sum));
                }
                else
                {
                    stack.Push(sum);
                }
            }
        }
        private void Read(Type t)
        {
            var read = Console.ReadLine();
            int resultInt;
            if (int.TryParse(read, out resultInt))
            {
                stack.Push( resultInt);
                return;
            }
            float resultFloat;
            if (float.TryParse(read, out resultFloat))
            {
                stack.Push( resultFloat);
                return;
            }
            bool resultBool;
            if (bool.TryParse(read, out resultBool))
            {
                stack.Push(resultBool);
                return;
            }
            else
            {
                if (t == Type.String) {
                    Console.WriteLine("String");
                }

                stack.Push(read);
                return;
            }
        }



    }
}
