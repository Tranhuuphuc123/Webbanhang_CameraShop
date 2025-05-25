import { redirect } from "next/navigation";

//dung redirect trong nextjs để chuyển hướng thẳng đến trang page.tsx của client
export default function Home() {
  redirect("/client");
}
