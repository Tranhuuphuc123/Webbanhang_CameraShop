//tạo component carousel image để thiết kế components chứa img và text của ảnh cần hiển thị
"use client";
// components/ExampleCarouselImage.tsx
import React from "react";
import Image from "next/image";

interface ExampleCarouselImageProps {
  text: string;
}

const ExampleCarouselImage: React.FC<ExampleCarouselImageProps> = ({
  text,
}) => (
  <div
    style={{
      width: "100%",
      height: "600px",
      position: "relative",
      background: "#777",
    }}
  >
    {/* Bạn có thể thay thế src bằng ảnh thật nếu muốn */}
    <Image
      src="/assets/client/img/anhtemp/bg-1.jpg"
      alt={text}
      layout="fill"
      objectFit="cover"
      style={{ opacity: 0.7 }}
    />
    <div
      style={{
        position: "absolute",
        top: "50%",
        left: "50%",
        transform: "translate(-50%, -50%)",
        color: "#fff",
        fontSize: "2rem",
        fontWeight: "bold",
        textShadow: "0 2px 8px rgba(0,0,0,0.5)",
        textAlign: "center",
        width: "100%",
      }}
    >
      {text}
    </div>
  </div>
);

export default ExampleCarouselImage;
