import { Box, Flex, Stack, Input, Button, Select } from "@chakra-ui/react";
import { SearchIcon, CheckCircleIcon } from "@chakra-ui/icons";
import {
  deleteLocalStorage,
  LOCAL_STORAGE_WORD_KEY,
  LOCAL_STORAGE_RATING_KEY,
  setLocalStorage,
} from "../../../infras/localStorage";

// TODO: APIで管理
let checkBoxItems = [
  { id: "rating-1", displayName: "評価5", value: 5 },
  { id: "rating-2", displayName: "評価4以上", value: 4 },
  { id: "rating-3", displayName: "評価3以上", value: 3 },
];

export type Props = {
  search: any;
  word: string;
  setWord: any;
  rating: string;
  setRating: any;
};

export const Sideber: React.VFC<Props> = (props) => {
  function clearFunc() {
    props.setWord("");
    props.setRating("");
    deleteLocalStorage();
  }

  const changeWord = (e: any) => {
    const inputWord = e.target.value;
    props.setWord(inputWord);
    setLocalStorage(LOCAL_STORAGE_WORD_KEY, inputWord);
  };

  const changeRating = (e: any) => {
    const selectRating = e.target.value;
    props.setRating(selectRating);
    setLocalStorage(LOCAL_STORAGE_RATING_KEY, selectRating);
  };

  return (
    <>
      <Flex gap="3">
        <Box display="flex" w="400px" bgColor="gray.50" p="8">
          <Stack spacing={[1, 5]} w="100%">
            <span>
              <SearchIcon /> フリーワード
            </span>
            <Input
              placeholder="Search"
              bg={"white"}
              mt={5}
              onChange={changeWord}
              value={props.word ?? ""}
            />
            <span>
              <CheckCircleIcon /> 評価
            </span>
            <Select
              placeholder="評価選択"
              onChange={changeRating}
              value={props.rating}
            >
              {checkBoxItems.map((item, key) => (
                <option key={key} value={item.id}>
                  {item.displayName}
                </option>
              ))}
            </Select>
            <Button onClick={() => clearFunc()}>clear</Button>
          </Stack>
        </Box>
      </Flex>
    </>
  );
};
