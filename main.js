const { app, BrowserWindow } = require('electron');
const { ipcMain } = require('electron');
const path = require('path');


(async () => {
    const Store = (await import('electron-store')).default;
    const store = new Store();


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
            nodeIntegration: false,
            contextIsolation: true,
            preload: path.join(__dirname, 'preload.js')
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

ipcMain.on('get-data', (event, key) => {
    const value = store.get(key);
    event.reply('send-data', value);
});

ipcMain.on('set-data', (event, key, value) => {
    store.set(key, value);
});

ipcMain.on('resize-window', (event, { width, height }) => {
    const win = BrowserWindow.getFocusedWindow();
    if (win) {
      win.setSize(width, height);
    }
});
  
  })();
  