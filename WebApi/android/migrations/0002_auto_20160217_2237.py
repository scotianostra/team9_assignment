# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('android', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='staff',
            name='hash',
            field=models.CharField(null=True, max_length=128),
        ),
        migrations.AddField(
            model_name='student',
            name='hash',
            field=models.CharField(null=True, max_length=128),
        ),
    ]
