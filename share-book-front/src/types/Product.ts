import { Book } from "./Book";
import { UserAccount } from "./UserAccount";

export class Product {
  constructor(
    public id = "",
    public book: Book = new Book(),
    public userAccount: UserAccount = new UserAccount(),
    public description = "",
    public returnDate = "",
    public rentalPeriod = 0,
    public averageRating = 0,
    public numberofReviews = 0
  ) {}
}
