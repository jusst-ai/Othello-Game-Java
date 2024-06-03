class Chip{

    private boolean black;
    

    Chip(boolean black){
        this.black = black;
    }

    public boolean getBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Chip other = (Chip) obj;
        if (black != other.black)
            return false;
        return true;
    }

    public String toString() {
        
            if(black){
                return "B";
            }
        
        return "W";
    }
}