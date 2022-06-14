import { UserAccount } from "./UserAccount";

export class Review {
    constructor(
      public id = '',
      public userAccount:UserAccount = new UserAccount(),
      public evaluation = 0,
      public comment = ''
    ) {}
  }