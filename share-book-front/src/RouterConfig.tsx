import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { NotFound } from "./components/page/common/NotFound";
import { Top } from "./components/page/Top";
import Header from "./components/page/common/Header";
import Footer from "./components/page/common/Footer";
import { Container } from "@chakra-ui/react";
import { Detail } from "./components/page/Detail";

export const RouterConfig: React.VFC = () => {
  return (
    <>
      <Header />
      <Container pt="5%">
        <BrowserRouter>
          <Routes>
            <Route index element={<Top />} />
            <Route path="*" element={<NotFound />} />
            <Route path="detail/:id" element={<Detail />} />
          </Routes>
        </BrowserRouter>
      </Container>
      <Footer />
    </>
  );
};
