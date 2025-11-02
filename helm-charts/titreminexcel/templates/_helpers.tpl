{{/*
Common labels
*/}}
{{- define "titreminexcel.labels" -}}
app.kubernetes.io/name: {{ include "titreminexcel.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}
