package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Stack stack = new Stack();

    public static int ACC = 0;
    public static int IP = 0;
    public static ArrayList<elem_t> Vars = new ArrayList<>();
    public static int NumVars = 0;
    public static ArrayList<elem_t> Labels = new ArrayList<>();
    public static int NumLabels = 0;
    public static ArrayList<instruct_t> Instructs = new ArrayList<>();
    public static int NumInstructs = 0;
    public static int NumNumbers = 0;

    public static void error(String s){
        System.out.println("Error: " + s);
        System.exit(0);
    }
    public static void ADD(int a, int b){
        ACC+=Vars.get(a).getVal();
    }
    public static void SUB(int a, int b){
        ACC-=Vars.get(a).getVal();
    }
    public static void MULT(int a, int b){
        ACC*=Vars.get(a).getVal();
    }
    public static void DIV(int a, int b){
        ACC/=Vars.get(a).getVal();
    }
    public static void LOAD(int a, int b){
        ACC=Vars.get(a).getVal();
    }
    public static void STORE(int a, int b){
        Vars.get(a).setVal(ACC);
    }
    public static void COPY(int a, int b){
        Vars.get(a).setVal(Vars.get(b).getVal());
    }
    public static void READ(int a, int b){
        System.out.println("Give number: ");
        Scanner in = new Scanner(System.in);
        int num =in.nextInt();
        Vars.get(a).setVal(num);
        in.close();
    }
    public static void WRITE(int a,int b){
        System.out.println("Number is" + Vars.get(a).getVal());
    }
    public static void STOP(int a, int b){
        System.exit(0);
    }
    public static void NOOP(int a, int b){
        return;
    }
    public static void BR(int a, int b){
        IP=Labels.get(a).getVal()-1;
    }
    public static void BRNEG(int a, int b){
        if(ACC < 0){
            IP=Labels.get(a).getVal()-1;
        }
    }
    public static void BRZNEG(int a, int b){
        if(ACC<=0){
            IP=Labels.get(a).getVal()-1;
        }
    }
    public static void BRPOS(int a, int b){
        if(ACC > 0){
            IP=Labels.get(a).getVal()-1;
        }
    }
    public static void BRZPOS(int a, int b){
        if(ACC >= 0){
            IP=Labels.get(a).getVal()-1;
        }
    }
    public static void BRZERO(int a, int b){
        if(ACC==0){
            IP=Labels.get(a).getVal()-1;
        }
    }
    public static void PUSH(int a, int b){
        if(stack.getTos() == 1000-1){
            error("Stack overflow");
        }
        stack.setTos(stack.getTos() + 1);//increment tos
    }
    public static void POP(int a, int b){
        if(stack.getTos() < 0){
            error("Stack underflow");
        }
        stack.setTos(stack.getTos() - 1);//decrement tos
    }
    public static void STACKW(int a, int b){
        int loc;
        loc = stack.getTos() - Vars.get(a).getVal();
        if(loc < 0 || loc > stack.getTos()){
            error("Stack write error");
        }
        stack.setLoc(loc,ACC);
    }
    public static void STACKR(int a, int b){
        int loc;
        loc = stack.getTos() - Vars.get(a).getVal();
        if(loc < 0 || loc > stack.getTos()){
            error("Stack read error");
        }
        ACC=stack.getLoc(loc);
    }

    public static String Reserved[]={"ADD","SUB","MULTI","DIV","LOAD","STORE","COPY","READ",
            "WRITE","STOP","NOOP","BR","BRNEG","BRZNEG","BRPOS","BRZPOS","BRZERO",
            "PUSH","POP","STACKW","STACKR",""};

    public static instructInfo_t InstructInfo[]={
            new instructInfo_t("ADD",1,1),
            new instructInfo_t("SUB",1,1),
            new instructInfo_t("MULT",1,1),
            new instructInfo_t("DIV",1,1),
            new instructInfo_t("LOAD",1,1),
            new instructInfo_t("STORE",1,0),
            new instructInfo_t("COPY",2,0),
            new instructInfo_t("READ",1,0),
            new instructInfo_t("WRITE",1,1),
            new instructInfo_t("STOP",0,0),
            new instructInfo_t("NOOP",0,0),
            new instructInfo_t("BR",1,0),
            new instructInfo_t("BRNEG",1,0),
            new instructInfo_t("BRZNEG",1,0),
            new instructInfo_t("BRPOS",1,0),
            new instructInfo_t("BRZPOS",1,0),
            new instructInfo_t("BRZERO",1,0),
            new instructInfo_t("PUSH",0,0),
            new instructInfo_t("POP",0,0),
            new instructInfo_t("STACKW",1,1),
            new instructInfo_t("STACKR",1,1)
    };

    public static int inReserved(String s){
        int i;
        for(i=0;;i++){
            if(Reserved[i].equals("")){
                return -1;
            }
            if(Reserved[i].equals(s)){
                return i;
            }
        }
    }

    public static int nothing(String s){
        if(s == null || s.matches("\\s+") || s.isEmpty()){
            return 1;
        }
        else
            return 0;
    }

    public static int isNumber(String s){
        int i = 0;
        if(s == null || s.isEmpty()){return 0;}
        if((s.charAt(0) == '-' || s.charAt(0) == '+')){
            if(!Character.isDigit(s.charAt(1))) {
                error("Minus/plus by itself");
            }else{
                i++;
            }
        }
        for(i = i; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i))){
                return 0;
            }
        }
        return 1;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        ArrayList<String> input = new ArrayList<>();

        while(in.hasNextLine()){
            String line = in.nextLine();
            if(line.length() == 0){
                break;
            }
            input.add(line);
        }
       // pass1(input);
        //For test pass1
        //System.out.println("NumLabels: " + NumLabels + " NumNumbers: " + NumNumbers + " NumVars: " + NumVars + " NumInstructs: " + NumInstructs);
        /*Vars = new elem_t[NumVars+NumNumbers];//counts labels,numbers,instructions, and variables
        Labels = new elem_t[NumLabels];
        instructs = new instruct_t[NumInstructs];*/
        pass2(input);//writes variables and labels
        //For testing pass2
        System.out.println("Labels: " + Labels.toString());
        System.out.println("Variables: " + Vars.toString());
        pass3(input);

    }

    public static int findInTable(ArrayList<elem_t> table, String name){
        for(int i = 0; i < table.size(); i++){
            if(table.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }


    public static void pass3(ArrayList<String> input){
        int numInstructs = 0, instruct, label;
        String line;
        for(int i = 0; i < input.size(); i++){
            if(nothing(input.get(i)) == 1){continue;}
            int index = -1;
            index = input.get(i).indexOf(":");
            if(index > 0){
                index++;//Move index after label
                line = input.get(i).substring(index).trim();
            }else {
                line = input.get(i);
            }
            String[] tokens = line.split(" ");
            for(int k = 0; k < tokens.length; k++){
                instruct = inReserved(tokens[k]);
                if(instruct >= 0){
                    instruct_t ins = new instruct_t();
                    Instructs.add(ins);
                    Instructs.get(numInstructs).setFunctionName(InstructInfo[numInstructs].getFunctionName());
                    for(int m = 0; m < InstructInfo[instruct].numArgs; m++){
                        if(k + 1 < tokens.length) {
                            k++;
                            if(instruct >=11 && instruct <= 16){//BR's
                                label = findInTable(Labels,tokens[k]);
                                if(label < 0){
                                    error("BRs must be followed by defined labels");
                                }
                                Instructs.get(numInstructs).setArg(m,label);
                            }else{
                                    label = findInTable(Vars, tokens[k]);
                                    if(label < 0){
                                        error("Unknown argument");
                                    }
                                    Instructs.get(numInstructs).setArg(m,label);
                            }
                        }
                    }
                    numInstructs++;
                }
            }
        }
    }

    public static void pass2(ArrayList<String> input){
        int numLabels = 0, numVarsNumbers = 0, numInstructs = 0, instruct = -1;
        String name = null, line;
        for(int i = 0; i < input.size(); i++){
            if(nothing(input.get(i)) == 1){continue;};
            int index = -1;
            index = input.get(i).indexOf(":");
            if(index > 0){
                elem_t Label = new elem_t();
                Labels.add(Label);
                name = input.get(i);
                name = name.substring(0,index);
                Labels.get(numLabels).setName(name);
                Labels.get(numLabels).setVal(numInstructs);
                numLabels++;
                index++;
                line = input.get(i).substring(index).trim();
            }else{
                line = input.get(i);
            }

            String[] tokens = line.split(" ");
            for(int k = 0; k < tokens.length; k++){
                if(nothing(tokens[k]) == 1){
                    continue;
                }
                instruct = inReserved(tokens[k]);
                if(instruct < 0){
                    elem_t var = new elem_t();
                    Vars.add(var);
                    Vars.get(numVarsNumbers).setName(tokens[k]);
                    for(int m = 0; m < numVarsNumbers; m++){
                        if(Vars.get(m).getName() == tokens[k]){
                            error("Multiply Defined Variable\n");
                        }
                    }
                    if(k + 1 < tokens.length) {
                        k++;
                        if(tokens[k] == null || isNumber(tokens[k]) == 0){
                            error("Variable name must be followed by integer");
                        }
                        Vars.get(numVarsNumbers).setVal(Integer.parseInt(tokens[k]));
                    }
                    if(k + 1 < tokens.length) {
                        k++;
                        if(nothing(tokens[k]) == 0){
                            error("Left over on variable definition line");
                        }
                    }
                    numVarsNumbers++;
                }else{//instruction
                    for(int m = 0; m < InstructInfo[instruct].numArgs; m++){
                        if(k + 1 < tokens.length) {
                            k++;
                            if((tokens[k] == null) || (InstructInfo[instruct].useImmediate == 0 && isNumber(tokens[k]) == 0)){
                                error("Invalid argument");
                            }if(isNumber(tokens[k]) == 1){
                                elem_t var = new elem_t();
                                Vars.add(var);
                                Vars.get(numVarsNumbers).setName(tokens[k]);
                                Vars.get(numVarsNumbers).setVal(Integer.parseInt(tokens[k]));
                                numVarsNumbers++;
                            }
                        }
                    }
                    numInstructs++;
                }

            }
        }
    }

   /* public static void pass1(ArrayList<String> input){
        String line;
        int instruct = -1;
        for(int i = 0; i < input.size(); i++){
            if(nothing(input.get(i)) == 1){
                continue;
            }
            int index = -1;
            index = input.get(i).indexOf(":");
            if(index > 0){
                NumLabels++;
                index++;//Move index after label
                line = input.get(i).substring(index).trim();
            }else {
                line = input.get(i);
            }

            String[] tokens = line.split(" ");
            for(int k = 0; k < tokens.length; k++){
                if(nothing(tokens[k]) == 1){
                    continue;
                }
                instruct = inReserved(tokens[k]);
                if(instruct >= 0){
                    NumInstructs++;
                    if(InstructInfo[instruct].useImmediate == 1){
                        if(isNumber(tokens[k+1]) == 1) {
                            NumNumbers++;
                            break;
                        }else{
                            break;
                        }
                    }else{
                        break;
                    }
                }else if(isNumber(tokens[k]) == 0){
                    NumVars++;
                    System.out.println(tokens[k]);
                    break;
                }
            }
        }
    }*/
}
