package model

// PluginInfo describes a remote plugin in an Atom's pluginInfos array.
type PluginInfo struct {
	Name           string `json:"name"`
	Version        int64  `json:"version"`
	LastUpdateTime int64  `json:"lastUpdateTime"`
	PluginStatus   int64  `json:"pluginStatus"`
	ClassName      string `json:"className"`
}

// LocalPluginInfo is the server-side plugin configuration.
type LocalPluginInfo struct {
	ID               int64  `json:"id"`
	Name             string `json:"name"`
	URL              string `json:"url"`
	MD5              string `json:"md5"`
	ClassName        string `json:"className"`
	NeedRun          bool   `json:"needRun"`
	NeedUpdate       bool   `json:"needUpdate"`
	DelayRunSeconds  int64  `json:"delayRunSeconds"`
	LastVersion      int64  `json:"lastVersion"`
	Password         string `json:"password"`
	PluginStatus     int64  `json:"pluginStatus"`
	EndDelete        bool   `json:"endDelete"`
	AutoStartOnInit  bool   `json:"autoStartOnInit"`
	StartIndex       int64  `json:"startIndex"`
	RunInSubProcess  bool   `json:"runInSubProcess"`
}
