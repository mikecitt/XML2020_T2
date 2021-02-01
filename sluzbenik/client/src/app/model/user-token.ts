export interface UserToken {
    id?: number;
    authorities: string[];
    token: string;
    expireIn: number;
}
