export class UserAccount {
    constructor(
      id = '',
      email = '',
      phoneNumber = '',
      detail: UserDetail = new UserDetail()
    ) {}
  }
  
  export class UserDetail {
    constructor(
      public firstName = '',
      public lastName = '',
      public nickName = '',
      public birthDay = '',
      public profile = '',
      public address = ''
    ) {}
  }
  