import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  //cấu hình cho phép load ảnh từ domain localhost
  images: {
    domains: ["localhost"], 
  },
};

export default nextConfig;
