package Camera24h.com.vn.Appbanhang.enity;

import java.util.Objects;


/* cach sử dụng khóa chính kép (composite primary key) dùng @IdClass để khai báo khóa chính kép.*/
public class ShopOrderDetailsId {
    private int orderId;
    private int productId;

    public ShopOrderDetailsId() {}

    public ShopOrderDetailsId(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    // Cần override equals() và hashCode() để đảm bảo hoạt động đúng
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopOrderDetailsId that = (ShopOrderDetailsId) o;
        return orderId == that.orderId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
}
