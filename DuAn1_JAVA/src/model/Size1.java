package model;

/**
 *
 * @author ADMIN
 */
public class Size1 {
    private int MaSize;
    private String KichThuoc;
    private boolean TrangThai;

    public Size1() {
    }

    public Size1(int MaSize, String KichThuoc, boolean TrangThai) {
        this.MaSize = MaSize;
        this.KichThuoc = KichThuoc;
        this.TrangThai = TrangThai;
    }

    public Size1(String KichThuoc) {
        this.KichThuoc = KichThuoc;
    }

    public Size1(int MaSize){
        this.MaSize=MaSize;
       
    }


    
    public int getMaSize() {
        return MaSize;
    }

    public void setMaSize(int MaSize) {
        this.MaSize = MaSize;
    }

    public String getKichThuoc() {
        return  KichThuoc;
    }

    public void setKichThuoc(String KichThuoc) {
        this.KichThuoc = KichThuoc;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }


    
//    public Size1() {
//    }
//
//    public Size1(int MaSize, int KichThuoc, boolean TrangThai) {
//        this.MaSize = MaSize;
//        this.KichThuoc = KichThuoc;
//        this.TrangThai = TrangThai;
//    }
//
//    public int getMaSize() {
//        return MaSize;
//    }
//
//    
//    public void setMaSize(int MaSize) {
//        this.MaSize = MaSize;
//    }
//
//    public int getKichThuoc() {
//        return KichThuoc;
//    }
//
//    public void setKichThuoc(int KichThuoc) {
//        this.KichThuoc = KichThuoc;
//    }
//
//    public boolean isTrangThai() {
//        return TrangThai;
//    }
//
//    public void setTrangThai(boolean TrangThai) {
//        this.TrangThai = TrangThai;
//    }
//
//    public Size1(int KichThuoc) {
//        this.KichThuoc = KichThuoc;
//    }
    
     public String getTrangThai(){
        String trangThai;
        if(this.isTrangThai()){
            trangThai = "Đang kinh doanh";
        }else{
            trangThai = "Ngừng kinh doanh";
        }
        return trangThai;
    }
    
    public Object[] dataSize(){
        return new Object[]{this.MaSize,this.KichThuoc,this.getTrangThai()};
    }
}
