export interface UserToken {
    id: string;
    authorities: string[];
    token: string;
    expireIn: number;
}
