# StringCalculator
This java program is written to calculate and solve mathematical equations (with variables) given at runtime by user. It is written in such a way that
other programmers or mathematicians need not to write same equation(with different variable values) twice. All it need to use
loops before call as according to need. I wrote it to help myself to solve various numerical problems i had to face in my 1st sem 
in a paper something called "numerical techniques". Using this i solved those probems easily in my PC without needing expertise hands
on casio fx82 calCs.I wrote this code in urgent kind of situation so this code lacks of important comments and explanations(pardon me for that).
Coming to point, you can use it to solve equations contains sin, cos, tan, cot, sec, cosec,log(base 10), ln.
To symbolize power you need to input '^' like "2^3" so it returns 8.0 or '√' for square root like: √4 or use '!' to find factorial or π for pi etc .This also can calculates permutation and combinations use: 8p2, 8c2.

Ex: stringEvaluater.evaluate("√(-4.1^2/y*cosec(30π/3!*x))*9p4", true, 2, new char[]{'x','y'}, new double[]{30,1})); gives 12842.201983298979

My E-Mail ID: bablushaw23@gmail.com 

To use this: 
You have to make object of StringEvaluater. then call evaluate() function.

evaluate has two signatures :
1. public Double evaluate(String eqn, boolean degree, int noOfVars, char[] varChar, double[] varValues)
	eqn: is your equation in string.
	degree: true if eqn contains any trigo fncs with degree, false for radians. Give true or false if eqn doesn't contain any trigos.
	noOfVars: asks for no. of variables used in equation. Ex: eqn:"2x^2-y" has noOfVars=2 for (x,y).
	varChar[]: put your array of variables(no. should be equal to noOfVars, take care of upper/lower cases and shouldn't be any letter used by any function. Ex: sin() uses s,i,n so not to use these. You may understand why).
	varValues[]: values of the variables in the same order and no. as vars contained in varChar[]. Ex: if varChar[]={y,x} and you want x=2, y=3 then varValues[]={3,2}.fst y then x as {y,x}.
	If you have no variables in eqn, no problem you can still use it. put any dummy arrays.
2. public Double evaluate(String eqn, boolean degree) 
	if you don't want variable headache.
	
By some basic java knowledge, you can call this function fitting your circumstances. I'll post some examples to show how i used this, some are: 
1. used for Eular's various methods to solve easily.
2. Runge- kutta 2nd, 4th 
3. Simson and trapazoidal
4. Regular-falsi
and some more.

If you feel any problem(regarding this, ok), please let me know.

And yes, since i used some unicode characters like '√' (root), 'π' etc in my code so it might be possible that source code may not execute properly.if you treat the files properly, like opening .java file in an editor which supports unicode format, will help.
