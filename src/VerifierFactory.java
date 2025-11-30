public class VerifierFactory {
    public static Verifier getVerifier(int mode) {
        
        if (mode == 0) {
            return new SequentialVerifier();
        } 
        else if (mode == 3) {
             return new Mode3Verifier();
        }

        else if (mode == 27) {
            return new Mode27Verifier();
            
        }
        
        return null;
    }
}
    

