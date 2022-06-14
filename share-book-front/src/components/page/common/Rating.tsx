import { Box, Text } from "@chakra-ui/react";
import React from "react";
import { MdStar } from "react-icons/md";
import { Product } from "../../../types/Product";

export type Props = {
  product: Product;
};

function getAverageRating(product: Product) {
  //総合評価 / 評価数 =　平均評価
  return (product.totalReting / product.count).toFixed(2);
}

export const Rating: React.VFC<Props> = (props) => {
  getAverageRating(props.product);
  return (
    <>
      <Box as={MdStar} color="orange.400" ml={3} />
      <Text ml={1} fontSize="sm">
        {/*  TODO:APIで取得した評価、件数を表示 */}
        <b>{getAverageRating(props.product)}</b> ({props.product.count})
      </Text>
    </>
  );
};

export default Rating;
