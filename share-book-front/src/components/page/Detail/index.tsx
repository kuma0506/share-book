import { Box, Button, Heading, Image, Stack } from "@chakra-ui/react";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { init } from "../../../infras/ApiCliant";
import { Product } from "../../../types/Product";

export const Detail: React.VFC = () => {
  const params = useParams();
  init();
  const [product, setProduct] = useState(new Product());
  useEffect(() => {
    axios.get(`api/products/${params.id}`).then((res) => {
      setProduct(res.data);
    });
  }, []);

  return (
    <>
      <Box boxSize="xl" borderWidth="1px" borderRadius="lg" overflow="hidden">
        <Image src={`${process.env.PUBLIC_URL}/book.jpeg`} alt="Dan Abramov" />
        <Box m={4}>
          <span>
            <Heading as="h3" size="md" pb={2}>
              {product.book.name}
            </Heading>
            {product.description}
          </span>
          <Stack
            direction="row"
            spacing={4}
            align="center"
            justify="center"
            m={3}
          >
            <Button colorScheme="teal" variant="solid">
              申込
            </Button>
            <Link to={"/"}>
              <Button colorScheme="teal" variant="outline">
                戻る
              </Button>
            </Link>
          </Stack>
        </Box>
      </Box>
    </>
  );
};
