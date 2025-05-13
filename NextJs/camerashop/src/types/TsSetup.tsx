/* mục này có chức năng là setup thiết lặp kiểu dữ liệu dùng chung 
tsx cho cả dự án -> định nghĩa kiểu dữ liệu typerscript */

/*******tạo interface cho dữ liệu sản phẩm products *******/
export interface ProductTypes {
    id: string;
    productCode: string;
    productName: string;
    image: string;
    shortDescription: string;
    description: string;
    standardCost: number;
    listPrice: number;
    quantityPerUnit: string;
    discontinued: boolean;
    isFeatured: boolean;
    isNew: boolean;
    supplierId: number;
    categoryId: number;
    createAt: string;
    updateAt: string;
}


/***inter face cho dữ liệu category *******/
export interface CategoryTypes {
    id: number;
    categoryCode: string;
    categoryName: string;
    description: string;
    image: string;
    createAt: string;
    updateAt: string;
}


/******inter face cho dữ liệu supplier *******/
export interface SupplierTypes {
    id: number;
    supplierCode: string;
    supplierName: string;
    description: string;
    image: string;
    createAt: string;
    updateAt: string;
}


/***interface cho dữ kiểu  trả về response của hàm fetchProduct*******/
export interface ApiResponseTypes {
    data: ProductTypes[];
    totalPage: number;
    totalElement: number;
}


/***interface cho dữ kiểu dữ liệu cho page.tsx của create product -> truyền props cho page *******/
export interface CreateProductPropsTypes {
//    listProduct: ProductTypes[];
//    setListProduct: React.Dispatch<React.SetStateAction<ProductTypes[]>>;
   onReload?: () => void; //? truyền cũng đc mà không cũng đc
}

/**nterface cho kiểu dữ liệu cho page.tsx của edit product -> truyền props cho page *******/
export interface EditProductPropsTypes {
    id: string;
    onReload?: () => void;
}


/**interface cho kiểu dữ liệu của page.tsx của delete product -> truyền props cho page**/
export interface DeleteProductPropsTypes {
    id: string;
    onReload?: () => void;
}

/**interface cho xóa hàng loạt**/
export interface BatchDeletePropsTypes {
    ids: string[];
    onReload?: () => void;
}

/**khai báo types cho modal context tự viết **/
export interface ModalContextType {
    openModal: (type: string) => void;
    closeModal: () => void;
    show: boolean;
    modalType: string | null;
}


/**khai báo interfaces types cho modalToast tự viết thông báo***/
export interface ToastContextType {
    showToast: (type: string, message: string) => void;
}
