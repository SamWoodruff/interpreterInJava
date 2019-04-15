package classes;

public class instructInfo_t {
    private String functionName;
    int numArgs;
    int useImmediate;

    public instructInfo_t(String functionName, int numArgs, int useImmediate) {
        this.functionName = functionName;
        this.numArgs = numArgs;
        this.useImmediate = useImmediate;
    }
}
