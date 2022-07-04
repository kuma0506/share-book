export const LOCAL_STORAGE_WORD_KEY = "word";
export const LOCAL_STORAGE_RATING_KEY = "rating";

export function getLocalStorage(key: string): any {
    return localStorage.getItem(key);
}

export function setLocalStorage(key: string, value: string) {
    localStorage.setItem(key,value);
}

export function deleteLocalStorage() {
    localStorage.clear();
}