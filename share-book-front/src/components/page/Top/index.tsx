import {
  Box,
  Button,
  Flex,
  Grid,
  Heading,
  Image,
  Stack,
} from "@chakra-ui/react";
import axios from "axios";
import React, { useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";
import { init } from "../../../infras/ApiCliant";
import { getLocalStorage, setLocalStorage } from "../../../infras/localStorage";
import { Product } from "../../../types/Product";
import Rating from "../common/Rating";
import { Sideber } from "../common/Sideber";

const LOCAL_STORAGE_WORD_KEY = "word";

export const Top: React.VFC = () => {
  init();

  const [products, setProducts] = useState([]);
  const [word, setWord] = useState("");
  const didMountRef = useRef(false);

  let emptyBoxes = 9 - products.length;

  useEffect(() => {
    //mount時はスキップ
    if (!didMountRef.current) {
      didMountRef.current = true;
    } else {
      search();
    }
  }, [word]);

  function search() {
    axios
      .get("api/products/search", {
        params: {
          //TODO useStateのwordで検索するように修正
          word: getLocalStorage(LOCAL_STORAGE_WORD_KEY) ?? "",
          rating: 0,
        },
      })
      .then((res) => {
        handleUpdate(res.data);
      });
  }

  function handleUpdate(products: any) {
    setProducts(products);
    emptyBoxes = 9 - products.length;
  }

  function updatePropaty(word: string) {
      setWord(word);
      setLocalStorage(LOCAL_STORAGE_WORD_KEY, word);
  }

  return (
    <>
      <Heading as="h1" size="xl" mt="3%" mb="8%">
        検索結果：{products.length}件
      </Heading>
      <Box display="flex" justifyContent="center">
        <Grid
          templateColumns="repeat(3, 1fr)"
          gap={6}
          justifyContent="center"
          mr="15%"
        >
          {products.map((product: Product, key) => (
            <Box
              key={key}
              boxSize="md"
              borderWidth="1px"
              borderRadius="lg"
              overflow="hidden"
            >
              <Image
                src={`${process.env.PUBLIC_URL}/book.jpeg`}
                alt="Dan Abramov"
              />
              <Box m={4}>
                <Flex mt={2} align="center" justifyContent="center">
                  <Heading as="h4" size="md" pb={2}>
                    {product.book.name}
                  </Heading>
                  <Rating product={product} />
                </Flex>
                <span>{product.description}</span>
                <Stack
                  direction="row"
                  spacing={4}
                  align="center"
                  justify="center"
                  mt={1}
                >
                  <Link to={"detail/" + product.id}>
                    <Button colorScheme="teal" variant="solid">
                      詳細
                    </Button>
                  </Link>
                  <Button colorScheme="teal" variant="outline">
                    申込
                  </Button>
                </Stack>
              </Box>
            </Box>
          ))}
          {Array(emptyBoxes)
            .fill(0)
            .map((item, key) => {
              return <Box key={key} boxSize="md" overflow="hidden"></Box>;
            })}
        </Grid>
        <Sideber search={() => search()} updatePropaty={(e: any) => updatePropaty(e)} word={word}/>
      </Box>
    </>
  );
};