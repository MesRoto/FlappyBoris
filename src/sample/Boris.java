package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;



public class Boris extends Esine{
    double painovoima = PelinAsetukset.PainoVoima;
    double hyppyVoima = PelinAsetukset.HyppyVoima;
    boolean ilmassa = false;
    boolean tormannyt = false;
    Image juoksuKuva = new Image("file:images/running.gif");
    Image hyppyKuva = new Image("file:images/jumping.png");
    Image tippumisKuva = new Image("file:images/landing.png");

    public Boris() { //TODO: voi myös liikkua x akselilla?
        super(new Rectangle(PelinAsetukset.BorisLeveys, PelinAsetukset.BorisKorkeus), 100, PelinAsetukset.MaanKorkeus);
        super.getHahmo().setFill(new ImagePattern(juoksuKuva));
    }

    public void hyppaa() {
        if(!this.ilmassa){
            super.setLiike(this.hyppyVoima);
            super.asetaKuva(hyppyKuva);
        }
    }

    public void paivita() {
        this.paivitaIlmassa();
        this.liike += this.painovoima;
        if(this.ilmassa && this.liike > 0){
            super.asetaKuva(tippumisKuva);
        }
        super.setY(super.getY() + this.liike);
        if(this.liike >= PelinAsetukset.MaxPutoamisNopeus){  //max alaspäin putoamisvauhti
            this.liike = PelinAsetukset.MaxPutoamisNopeus;
        }
        if(super.getHahmo().getTranslateY() >= PelinAsetukset.MaanKorkeus){
            super.getHahmo().setTranslateY(PelinAsetukset.MaanKorkeus);
        }

    }
    public void paivitaIlmassa(){
        if(this.getHahmo().getTranslateY() - PelinAsetukset.MaanKorkeus == 0){
            this.ilmassa = false;
            super.asetaKuva(juoksuKuva);
        }
        else {
            this.ilmassa = true;
        }
    }
    public void tarkistaTormays(Este esine){
        Shape tormaysAlue = Shape.intersect(super.getHahmo(), esine.getHahmo());
        if(tormaysAlue.getBoundsInLocal().getWidth() > PelinAsetukset.HeittoHitDetecionissa){
            this.tormannyt = true;
        }
    }
}
