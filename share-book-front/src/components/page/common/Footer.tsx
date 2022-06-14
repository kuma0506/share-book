import { Container, Stack, Text } from "@chakra-ui/react";
import React from "react";

export const Footer: React.VFC = () => {
  return (
    <>
      <Container
        maxW="base"
        as="footer"
        role="contentinfo"
        bg="teal.500"
        color="white"
        mt="10%"
      >
        <Stack
          pt="8"
          pb="8"
          justify="center"
          direction={{ base: "column-reverse", md: "row" }}
          align="center"
        >
          <Text fontSize="md" color="subtle">
            &copy; {new Date().getFullYear()} Chakra UI Pro, Inc. All rights
            reserved.
          </Text>
        </Stack>
      </Container>
    </>
  );
};

export default Footer;
