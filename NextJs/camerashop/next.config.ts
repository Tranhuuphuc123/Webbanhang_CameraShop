import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  //cấu hình cho phép load ảnh từ domain localhost
  images: {
    domains: [
      "localhost",

      //chấp nhận ảnh từ domain cdn-icons-png.flaticon.com để load icon từ flaticon
      "cdn-icons-png.flaticon.com",

      //domain ảnh ngoài chưa được cấu hình
      "via.placeholder.com",

      //chấp nhận ảnh ngoài từ domain sau
      "upload.wikimedia.org",
    ],
    /*giải thích thêm:
      1/ "localhost": chấp nhận ảnh từ domain localhost
      2/ "cdn-icons-png.flaticon.com",: chấp nhận ảnh từ domain ngoài
    */
  },
};

export default nextConfig;
