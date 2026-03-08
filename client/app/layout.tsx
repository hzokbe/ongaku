import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";

export const metadata: Metadata = {
  title: {
    default: "Ongaku",
    template: "%s | Ongaku",
  },
  description: "Ongaku is a music streaming platform inspired by Spotify",
};

const inter = Inter({
  subsets: ["latin"],
});

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${inter.className} antialiased bg-neutral-50 text-neutral-950 min-h-dvh flex items-center justify-center`}
      >
        {children}
      </body>
    </html>
  );
}
