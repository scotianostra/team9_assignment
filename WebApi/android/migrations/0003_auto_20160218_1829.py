# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('android', '0002_auto_20160217_2237'),
    ]

    operations = [
        migrations.RenameField(
            model_name='class',
            old_name='classid',
            new_name='class_id',
        ),
        migrations.RenameField(
            model_name='module',
            old_name='moduleid',
            new_name='module_id',
        ),
        migrations.RenameField(
            model_name='staff',
            old_name='staffid',
            new_name='staff_id',
        ),
    ]
