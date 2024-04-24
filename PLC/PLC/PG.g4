grammar PG;

program: stat+ EOF;

stat: ptype ID (',' ID)* (';')+ # declarattion
    | expr (';')+ # emptyExpr
    | WRITE_KW expr (',' expr)*   (';')+ # writeExpr
    | READ_KW ID (',' ID)*   (';')+ # readExpr
    | COMMENT #comment
    | IF_KW '('expr')' stat (ELSE_KW stat)? # ifCon
    | WHILE_KW '('expr')' stat # whileLoop
    | FOR_KW '('expr ';' expr ';' expr ')' stat # forLoop
    | '{' stat+ '}' # blockStat
    ;

expr: expr op=(MUL|DIV|MOD) expr # mulDivMod
    | expr op=(ADD|SUB|CONCAT) expr # addSubConcat
    | expr op=(LT|GT|EQ|NEQ) expr # compare
    | expr op=AND expr # logicAnd
    | expr op=OR expr # logicOr
    | expr '?' expr ':' expr # ternary 
    | SUB expr # uminus
    | NOT expr # not
    | STRING # string
    | INT # int
    | ID # id
    | BOOL # boolean
    | FLOAT # float
    | '(' expr ')' # parenthesis
    | <assoc=right> ID '=' expr # assign
    ;

ptype: type=INT_KW
    | type=FLOAT_KW
    | type=BOOL_KW
    | type=STRING_KW
    ;

INT_KW : 'int';
FLOAT_KW : 'float';
STRING_KW : 'string';
BOOL_KW : 'bool';
WRITE_KW : 'write';
READ_KW : 'read';
WHILE_KW : 'while';
FOR_KW : 'for';
IF_KW : 'if' ;
ELSE_KW : 'else' ;
SEMI: ';';
COMMA: ',';
MUL : '*' ; 
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
MOD : '%' ;
GT : '>' ;
LT : '<' ;
EQ : '==' ;
AND : '&&' ;
OR : '||' ;
NOT : '!' ;
NEQ : '!=';
CONCAT : '.' ;
STRING :  ('"' [a-zA-Z0-9(){}<>,._!?:/*+%=; ]* '-'? [a-zA-Z0-9(){}<>,._!?:/*+%=; ]* '"') | '""';
BOOL : ('true'|'false');
ID : [a-zA-Z_] [a-zA-Z0-9_]* ; 
FLOAT : [0-9]+'.'[0-9]+ ;
INT : [0-9]+ ; 
WS : [ \t\r\n]+ -> skip ;
COMMENT: '//' [a-zA-Z0-9.()"*-+/%,; ]+;