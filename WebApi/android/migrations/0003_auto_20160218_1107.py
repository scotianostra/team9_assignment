# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('android', '0002_auto_20160217_2237'),
    ]

    operations = [
        migrations.RenameField(
            model_name='staff',
            old_name='staffid',
            new_name='staff_id',
        ),
    ]
