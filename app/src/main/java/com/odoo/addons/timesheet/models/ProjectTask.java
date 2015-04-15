/**
 * Odoo, Open Source Management Solution
 * Copyright (C) 2012-today Odoo SA (<http:www.odoo.com>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http:www.gnu.org/licenses/>
 *
 * Created on 10/4/15 5:59 PM
 */
package com.odoo.addons.timesheet.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

import odoo.ODomain;

public class ProjectTask extends OModel {
    public static final String TAG = ProjectTask.class.getSimpleName();
    public static final String AUTHORITY = "com.odoo.hr.addons.timesheet.models.project_task";

    OColumn name = new OColumn("Task Name", OVarchar.class).setSize(128).setRequired();
    OColumn date_deadline = new OColumn("Deadline", ODateTime.class);

    OColumn project_id = new OColumn("Project", ProjectProject.class, OColumn.RelationType.ManyToOne);
    OColumn user_id = new OColumn("Assigned to", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn reviewer_id = new OColumn("Reviewer", ResUsers.class, OColumn.RelationType.ManyToOne);
    OColumn work_ids = new OColumn("Work Summary", ProjectTaskWork.class, OColumn.RelationType.OneToMany);


    public ProjectTask(Context context, OUser user) {
        super(context, "project.task", user);
    }

    @Override
    public ODomain defaultDomain() {
        ODomain domain = new ODomain();
        domain.add("user_id", "=", getUser().getUser_id());
        return domain;
    }

    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}