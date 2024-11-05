import { User } from "./User.interface";

export interface Game {
    id: number;
    name: string;
    genre: string; 
    releaseYear: number;
    imageUrl: string; 
    user: User;  
    addedDate: Date;         
}
