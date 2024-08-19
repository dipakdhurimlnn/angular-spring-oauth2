export class TokenOb {

    public expires_in: number;
    public access_token: string;
    public token_type: string;


    constructor(expires_in?: number, access_token?: string, token_type?: string) {
        this.expires_in = expires_in || 0;
        this.access_token = access_token || '';
        this.token_type = token_type || '';

    }

}
