package fi.academy;

public class OtusAi {
    protected Otus otus;


    public OtusAi(Otus otus){
        this.otus = otus;
        this.otus.setOtusAi(this);
    }

    public void onUpdate(){
    }

    public void onEnter(int x, int y, Tile tile){
    }

    public void onNotify(String message, Object ... params){

    }

}

