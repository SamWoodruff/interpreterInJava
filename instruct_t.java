package classes;

public class instruct_t {
    private int[] args = new int[2];
    private String functionName;

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public int getArg1(){
        return args[0];
    }
    public void setArg(int index,int arg){
        this.args[index] = arg;
    }
    public int getArg2(){
        return args[1];
    }
    public void setArg2(int arg){
        this.args[1] = arg;
    }
}
