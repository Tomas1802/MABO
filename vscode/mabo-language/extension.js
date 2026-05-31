const vscode = require("vscode");
const cp = require("child_process");

function activate(context) {
  context.subscriptions.push(
    vscode.commands.registerCommand("mabo.runFile", () => runMabo(false)),
    vscode.commands.registerCommand("mabo.checkFile", () => runMabo(true))
  );
}

function runMabo(checkOnly) {
  const editor = vscode.window.activeTextEditor;
  if (!editor) {
    vscode.window.showWarningMessage("No hay archivo activo.");
    return;
  }

  const document = editor.document;
  if (document.isDirty) {
    document.save();
  }

  const executable = vscode.workspace.getConfiguration("mabo").get("executable") || "mabo";
  const args = checkOnly ? ["--check", document.fileName] : [document.fileName];
  const terminal = vscode.window.createTerminal(checkOnly ? "MABO Check" : "MABO Run");
  terminal.show();
  terminal.sendText([quote(executable), ...args.map(quote)].join(" "));
}

function quote(value) {
  return `"${String(value).replace(/"/g, '\\"')}"`;
}

function deactivate() {}

module.exports = { activate, deactivate };
