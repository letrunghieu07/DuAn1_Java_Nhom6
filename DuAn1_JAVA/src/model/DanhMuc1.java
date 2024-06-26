package model;

/**
 *
 * @author ADMIN
 */
public class DanhMuc1 {
    private int MaDM;
    private String TenDanhMuc;
    private boolean TrangThai;
    
    public DanhMuc1(){
    }

    public DanhMuc1(int MaDM, String TenDanhMuc, boolean TrangThai) {
        this.MaDM = MaDM;
        this.TenDanhMuc = TenDanhMuc;
        this.TrangThai = TrangThai;
    }

    public DanhMuc1(int MaDM) {
        this.MaDM = MaDM;
    }

    public DanhMuc1(String TenDanhMuc) {
        this.TenDanhMuc = TenDanhMuc;
    }
    
    public int getMaDM() {
        return MaDM;
    }

    public void setMaDM(int MaDM) {
        this.MaDM = MaDM;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String TenDanhMuc) {
        this.TenDanhMuc = TenDanhMuc;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public DanhMuc1(String TenDanhMuc, boolean TrangThai) {
        this.TenDanhMuc = TenDanhMuc;
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return TenDanhMuc;
    }
    
     public String getTrangThai(){
        String trangThai;
        if(this.isTrangThai()){
            trangThai = "Đang kinh doanh";
        }else{
            trangThai = "Ngừng kinh doanh";
        }
        return trangThai;
    }
    
    public Object[] dataDanhMuc(){
        return new Object[]{this.MaDM,this.TenDanhMuc,this.getTrangThai()};
    }
    
    
    
}
