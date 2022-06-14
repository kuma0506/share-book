import React from "react";
import reportWebVitals from "./reportWebVitals";
import { RouterConfig } from "./RouterConfig";
import { createRoot } from "react-dom/client";
import { ChakraProvider } from "@chakra-ui/react"

const container = document.getElementById("root");
if (container) {
  const root = createRoot(container);
  root.render(
    <React.StrictMode>
      <ChakraProvider>
        <RouterConfig />
      </ChakraProvider>
    </React.StrictMode>
  );
}

reportWebVitals();
