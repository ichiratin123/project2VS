using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using PLC;
using System.Globalization;
using System.IO;

public class Program
{
    public static void Main(string[] args)
    {
        Thread.CurrentThread.CurrentCulture = new CultureInfo("en-US");
        //var fileName = "input1.txt";
        //var fileName = "input2.txt";
        var fileName = "input3.txt";
        //var fileName = "errors.txt";

        Console.WriteLine("Parsing: " + fileName);
        var inputFile = new StreamReader(fileName);
        AntlrInputStream input = new AntlrInputStream(inputFile);
        PGLexer lexer = new PGLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PGParser parser = new PGParser(tokens);

        parser.AddErrorListener(new ErrorListener());

        IParseTree tree = parser.program();

        if (parser.NumberOfSyntaxErrors == 0)
        {
            var typeChecking = new TypeCheckingListener();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.Walk(typeChecking, tree);

            if (Errors.NumberOfErrors != 0)
            {
                Errors.PrintAndClearErrors();
            }
            else
            {
                CodeGeneratorVisitor generator = new CodeGeneratorVisitor(typeChecking.Types, typeChecking.SymbolTable);
                var code = generator.Visit(tree);
                Console.WriteLine(code);
                var codeFilePath = "output.txt";
                File.WriteAllText(codeFilePath, code);
                VirtualMachine vm = new VirtualMachine(File.ReadAllText(codeFilePath));
                vm.run();
            }
        }
    }
}
