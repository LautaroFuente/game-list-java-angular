const { app, BrowserWindow } = require('electron');


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
            //contextIsolation: false
        }
    });

    win.loadURL('http://localhost:4200');

    win.on('closed', () => {
        win = null;
    });
}

app.whenReady().then(() => {
    createWindow(0);

    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length === 0) {
            createWindow(0);
        }
    });
});

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit();
    }
});