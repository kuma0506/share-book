export function getLocalStorage(key: string):any {
    return localStorage.getItem(key);
}

export function setLocalStorage(key: string, value: string) {
    localStorage.setItem(key,value);
}

export function deleteLocalStorage(key: string) {
    localStorage.removeItem(key);
}