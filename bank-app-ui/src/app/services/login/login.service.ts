import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor() {
        this.generatePkcePair();
    }

    private codeVerifier: string = '';
    private codeChallenge: string = '';
    private codeChallengeMethod: string = 'S256';

    generateCodeVerifier(length: number = 128): string {
        const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~';
        let codeVerifier = '';
        for (let i = 0; i < length; i++) {
            const randomIndex = Math.floor(Math.random() * characters.length);
            codeVerifier += characters[randomIndex];
        }
        return codeVerifier;
    }

    async generateCodeChallenge(codeVerifier: string): Promise<string> {
        const encoder = new TextEncoder();
        const data = encoder.encode(codeVerifier);

        const hashBuffer = await crypto.subtle.digest('SHA-256', data);

        const hashArray = Array.from(new Uint8Array(hashBuffer));
        const base64String = btoa(String.fromCharCode(...hashArray));

        return base64String.replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '');
    }

    async updatePkcePair(): Promise<void> {
        if (!sessionStorage.getItem('code_verifier')) {
            this.codeVerifier = this.generateCodeVerifier();
            sessionStorage.setItem('code_verifier', this.codeVerifier);
        }
        if (!sessionStorage.getItem('code_challenge')) {
            this.codeChallenge = await this.generateCodeChallenge(this.codeVerifier);
            sessionStorage.setItem('code_challenge', this.codeChallenge);
        }
    }

    getCodeVerifier(): string {
        return sessionStorage.getItem('code_verifier') || '';
    }

    getCodeChallenge(): string {
        return sessionStorage.getItem('code_challenge') || '';
    }

    getCodeChallengeMethod(): string {
        return this.codeChallengeMethod;
    }

    async generatePkcePair(): Promise<{ codeVerifier: string; codeChallenge: string }> {
        await this.updatePkcePair();
        return {codeVerifier: this.getCodeVerifier(), codeChallenge: this.getCodeChallenge()};
    }

}
