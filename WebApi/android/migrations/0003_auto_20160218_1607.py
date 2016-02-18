# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('android', '0002_auto_20160217_2237'),
    ]

    operations = [
        migrations.CreateModel(
            name='Room',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, verbose_name='ID', serialize=False)),
                ('room_name', models.CharField(max_length=10)),
            ],
        ),
        migrations.RemoveField(
            model_name='class',
            name='room',
        ),
    ]
