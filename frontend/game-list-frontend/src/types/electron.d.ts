interface ElectronAPI {
    ipcRenderer: {
      send(channel: string, ...args: any[]): void;
      once(channel: string, listener: (event: Electron.IpcRendererEvent, ...args: any[]) => void): void;
    };
  }
  
  declare global {
    interface Window {
      electron: ElectronAPI;
    }
  }
  
  export {};
  
  