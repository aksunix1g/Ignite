
package entities;


public class Produit {
    private int idProduit;
    private String nomProduit;
    private String imageProduit ;
    private int categorieProduit ;
    private float prixProduit ;
    private int quantiteProduit;
    private String descProduit ;

    public Produit(int idProduit ,String nomProduit, String imageProduit, int categorieProduit, float prixProduit, int quantiteProduit, String descProduit) {
        this.idProduit=idProduit;
        this.nomProduit = nomProduit;
        this.imageProduit = imageProduit;
        this.categorieProduit = categorieProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
        this.descProduit = descProduit;
    }

    public Produit(String nomProduit, String imageProduit, int categorieProduit, float prixProduit,int quantiteProduit, String descProduit) {
        this.nomProduit = nomProduit;
        this.imageProduit = imageProduit;
        this.categorieProduit = categorieProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit=quantiteProduit;
        this.descProduit = descProduit;
    }
    
     

    public  int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }

    public int getCategorieProduit() {
        return categorieProduit;
    }

    public void setCategorieProduit(int categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    public float getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(float prixProduit) {
        this.prixProduit = prixProduit;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public String getDescProduit() {
        return descProduit;
    }

    public void setDescProduit(String descProduit) {
        this.descProduit = descProduit;
    }
    
    
    
    
    
}
