import { Box, Flex, Stack, Input, Button, Select } from "@chakra-ui/react";
import { SearchIcon, CheckCircleIcon } from "@chakra-ui/icons";
import { useEffect } from "react";
import {
  deleteLocalStorage,
  getLocalStorage,
} from "../../../infras/localStorage";

// TODO: APIで管理
let checkBoxItems = [
  { id: "rating-1", displayName: "評価5", value: 5 },
  { id: "rating-2", displayName: "評価4以上", value: 4 },
  { id: "rating-3", displayName: "評価3以上", value: 3 },
];

const LOCAL_STORAGE_WORD_KEY = "word";

export type Props = {
  search: any;
  updatePropaty: any;
  word: string;
};

export const Sideber: React.VFC<Props> = (props) => {
  useEffect(() => {
    props.updatePropaty(getLocalStorage(LOCAL_STORAGE_WORD_KEY) ?? "");
  }, []);

  function clearFunc() {
    props.updatePropaty("");
    deleteLocalStorage(LOCAL_STORAGE_WORD_KEY);
  }

  const handleChange = (e: any) => {
    props.updatePropaty(e.target.value);
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
              onChange={handleChange}
              value={props.word ?? ""}
            />
            <span>
              <CheckCircleIcon /> 評価
            </span>
            <Select placeholder="評価選択">
              {checkBoxItems.map((item, key) => (
                <option key={key} value={item.value}>
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
