import { app, BrowserWindow } from "electron";

const sizes = [
    { width: 800, height: 600 },
    { width: 1024, height: 768 },
    { width: 1280, height: 720 },
    { width: 1920, height: 1080 }
];

function createWindow(sizeIndex) {
    const { width, height } = sizes[sizeIndex];

    const win = new BrowserWindow({
        width: width,
        height: height,
        webPreferences: {
            nodeIntegration: true,
        }
    });

    win.loadURL('http://localhost:4200'); 
}

app.whenReady().then(() => createWindow(0));

