import { Box, Text } from "@chakra-ui/react";
import React from "react";
import { MdStar } from "react-icons/md";
import { Product } from "../../../types/Product";

export type Props = {
  product: Product;
};

export const Rating: React.VFC<Props> = (props) => {
  return (
    <>
      <Box as={MdStar} color="orange.400" ml={3} />
      <Text ml={1} fontSize="sm">
        <b>{props.product.averageRating.toFixed(2)}</b> (
        {props.product.numberofReviews})
      </Text>
    </>
  );
};

export default Rating;
