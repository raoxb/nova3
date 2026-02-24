package store

import (
	"database/sql"

	"nova2-server/internal/model"
)

type PluginRepo struct {
	db *sql.DB
}

func NewPluginRepo(db *sql.DB) *PluginRepo {
	return &PluginRepo{db: db}
}

func (r *PluginRepo) List() ([]model.LocalPluginInfo, error) {
	rows, err := r.db.Query(`SELECT id, name, url, md5, class_name, need_run, need_update, delay_run_seconds, last_version, password, plugin_status, end_delete, auto_start_on_init, start_index, run_in_sub_process FROM plugins ORDER BY start_index ASC`)
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var plugins []model.LocalPluginInfo
	for rows.Next() {
		var p model.LocalPluginInfo
		if err := rows.Scan(&p.ID, &p.Name, &p.URL, &p.MD5, &p.ClassName, &p.NeedRun, &p.NeedUpdate, &p.DelayRunSeconds, &p.LastVersion, &p.Password, &p.PluginStatus, &p.EndDelete, &p.AutoStartOnInit, &p.StartIndex, &p.RunInSubProcess); err != nil {
			return nil, err
		}
		plugins = append(plugins, p)
	}
	return plugins, rows.Err()
}

func (r *PluginRepo) Create(p model.LocalPluginInfo) (int64, error) {
	result, err := r.db.Exec(`INSERT INTO plugins (name, url, md5, class_name, need_run, need_update, delay_run_seconds, last_version, password, plugin_status, end_delete, auto_start_on_init, start_index, run_in_sub_process) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
		p.Name, p.URL, p.MD5, p.ClassName, p.NeedRun, p.NeedUpdate, p.DelayRunSeconds, p.LastVersion, p.Password, p.PluginStatus, p.EndDelete, p.AutoStartOnInit, p.StartIndex, p.RunInSubProcess)
	if err != nil {
		return 0, err
	}
	return result.LastInsertId()
}

func (r *PluginRepo) GetByID(id int64) (*model.LocalPluginInfo, error) {
	p := &model.LocalPluginInfo{}
	err := r.db.QueryRow(`SELECT id, name, url, md5, class_name, need_run, need_update, delay_run_seconds, last_version, password, plugin_status, end_delete, auto_start_on_init, start_index, run_in_sub_process FROM plugins WHERE id = ?`, id).
		Scan(&p.ID, &p.Name, &p.URL, &p.MD5, &p.ClassName, &p.NeedRun, &p.NeedUpdate, &p.DelayRunSeconds, &p.LastVersion, &p.Password, &p.PluginStatus, &p.EndDelete, &p.AutoStartOnInit, &p.StartIndex, &p.RunInSubProcess)
	if err == sql.ErrNoRows {
		return nil, nil
	}
	return p, err
}

func (r *PluginRepo) GetTaskFile() (content, version string, err error) {
	err = r.db.QueryRow(`SELECT content, version FROM task_files ORDER BY id DESC LIMIT 1`).Scan(&content, &version)
	if err == sql.ErrNoRows {
		return "", "0", nil
	}
	return
}

func (r *PluginRepo) SetTaskFile(content, version string) error {
	_, err := r.db.Exec(`INSERT INTO task_files (content, version) VALUES (?, ?)`, content, version)
	return err
}
